package com.boot.business.quartz.service;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.mapper.QuartzMapper;
import com.boot.business.quartz.service.QuartzService;
import com.boot.business.quartz.vo.QuartzVo;
import com.boot.config.quartz.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QuartzServiceImpl.java
 * @Author Xuhui
 * @Date 2022/8/1 14:50
 * @Description
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzMapper quartzMapper;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ApplicationContextHolder applicationContextHolder;

    @Override
    public Page<QuartzVo> getQuartzList(QuartzVo quartzVo, int page, int limit) {

        Page<QuartzVo> voPage = new Page<>(page, limit);
        List<QuartzVo> voList = quartzMapper.getQuartzList(quartzVo, voPage);
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public List<QuartzVo> getQuartzList(QuartzVo quartzVo) {
        return quartzMapper.getQuartzJobs(quartzVo);
    }

    @Override
    public void addQuartzJob(QuartzVo quartzVo) {
        quartzMapper.insertOrUpdateJob(quartzVo);
    }

    @Override
    public void startJob(QuartzVo quartzVo) throws ClassNotFoundException, SchedulerException {


        log.info("启动任务--{}", quartzVo);
        Class<?> aClass = null;
        if (StringUtils.contains(quartzVo.getQuartzJobClass(), ".")) {
            aClass = Class.forName(quartzVo.getQuartzJobClass());
        } else {
            aClass = applicationContextHolder.getBean(quartzVo.getQuartzJobClass()).getClass();
        }
        if (null == aClass) {
            log.info("{} 未找到对应任务类", quartzVo);
            return;
        }

        JobDataMap dataMap = null;
        if (StringUtils.isNotBlank(quartzVo.getQuartzJobParams())) {
            dataMap = new JobDataMap(JSONUtil.toBean(quartzVo.getQuartzJobParams(), Map.class));
        }

        JobKey jobKey = new JobKey(quartzVo.getQuartzJobId(), quartzVo.getQuartzJobGroup());
        JobBuilder jobBuilder = JobBuilder.newJob((Class<? extends Job>) aClass).withIdentity(jobKey);

        if (null != dataMap) {
            //如果此参数不为空，则设置执行参数
            jobBuilder.setJobData(dataMap);
        }
        JobDetail detail = jobBuilder.build();

        CronTrigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzVo.getQuartzJobCron()))
                //.startNow()
                .build();

        scheduler.start();  //开启调度器
        scheduler.scheduleJob(detail, trigger); //执行任务
    }

    @Override
    public void switchJobStatus(QuartzVo vo) throws SchedulerException, ClassNotFoundException {
        QuartzVo quartz = quartzMapper.getQuartzById(vo.getQuartzJobId());
        JobKey key = new JobKey(quartz.getQuartzJobId(), quartz.getQuartzJobGroup());
        JobDetail jobDetail = scheduler.getJobDetail(key);
        log.info("jobDetail----{}", jobDetail);
        if (StringUtils.equals("1", vo.getQuartzJobStatus())) {
            //启动
            if (null == jobDetail) {
                //未在调度队列的任务，开启
                startJob(quartz);
            } else {
                //已在调度队列的任务，重新启动
                scheduler.resumeJob(key);
            }
            quartz.setQuartzJobStatus("1");
            quartzMapper.updateQuartzJobById(quartz);
        } else {
            scheduler.pauseJob(key);
            quartz.setQuartzJobStatus("0");
            quartzMapper.updateQuartzJobById(quartz);
            log.info("停止任务--{}", quartz);
        }
    }

    @Override
    public void startOnce(QuartzVo quartzJob) throws
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, SchedulerException {

        //todo 执行一次
        JobKey jobKey = new JobKey(quartzJob.getQuartzJobId(), quartzJob.getQuartzJobGroup());

        if (scheduler.getJobDetail(jobKey) == null) {
            //如果调度器中没有，添加进去
            //this.startJob(quartzJob);
            log.info("立即执行一次--{}", quartzJob);
            Class<?> aClass = null;
            if (StringUtils.contains(quartzJob.getQuartzJobClass(), ".")) {
                aClass = Class.forName(quartzJob.getQuartzJobClass());
            } else {
                aClass = applicationContextHolder.getBean(quartzJob.getQuartzJobClass()).getClass();
            }
            if (null == aClass) {
                log.info("{} 未找到对应任务类", quartzJob);
                return;
            }

            JobDataMap dataMap = null;
            if (StringUtils.isNotBlank(quartzJob.getQuartzJobParams())) {
                dataMap = new JobDataMap(JSONUtil.toBean(quartzJob.getQuartzJobParams(), Map.class));
            }

            JobBuilder jobBuilder = JobBuilder.newJob((Class<? extends Job>) aClass).withIdentity(jobKey);

            if (null != dataMap) {
                //如果此参数不为空，则设置执行参数
                jobBuilder.setJobData(dataMap);
            }

            JobDetail detail = jobBuilder.build();
            //Trigger trigger = new SimpleTriggerImpl();
            SimpleTriggerImpl trigger = new SimpleTriggerImpl();
            trigger.setName("SimpleTrigger+planName+planId");
            trigger.setStartTime(new Date());// 开始运行时间
            trigger.setRepeatInterval(1);
            trigger.setRepeatCount(0); // 重复次数，设为0表示不重复
            //trigger.setEndTime(new Date(new Date().getTime() + 3600));
            //scheduler.start();  //开启调度器
            scheduler.scheduleJob(detail, trigger);
            //scheduler.triggerJob(jobKey);
        } else {
            scheduler.triggerJob(jobKey);
        }
    }


}
