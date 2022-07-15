package com.boot.business.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.business.quartz.mapper.QuartzJobMapper;
import com.boot.business.quartz.service.QuartzJobService;
import com.boot.business.quartz.vo.JobVO;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuartzJobServiceImpl.java
 * @Author Xuhui
 * @Date 2022/7/15 17:17
 * @Description
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Override
    public List<JobVO> listJobs(QueryWrapper queryWrapper) {
        return quartzJobMapper.selectList(queryWrapper);
    }

    @Override
    public void stopJob(JobVO job) throws SchedulerException {
        JobVO jobVO = quartzJobMapper.selectById(job.getJobId());   //根据主键查询
        JobKey jobKey = new JobKey(jobVO.getJobId(), jobVO.getJobGroup());
        scheduler.pauseJob(jobKey);
        scheduler.deleteJob(jobKey);
        jobVO.setJobStatus("0");
        quartzJobMapper.updateById(jobVO);
    }

    @Override
    public void startJob(JobVO jobVO) throws SchedulerException, ClassNotFoundException {
        JobVO job = quartzJobMapper.selectById(jobVO.getJobId());   //根据主键查询
        JobKey jobKey = new JobKey(jobVO.getJobId(), jobVO.getJobGroup());

        Class<?> aClass = Class.forName(job.getJobClass());
        JobDetail detail =
                JobBuilder.newJob((Class<? extends Job>) aClass)
                        .withIdentity(jobKey)
                        .build();
        CronTrigger trigger =
                TriggerBuilder.newTrigger()
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                        .build();
        scheduler.start();
        scheduler.scheduleJob(detail, trigger);
        jobVO.setJobStatus("1");
        quartzJobMapper.updateById(job);
    }
}
