package com.boot.business.quartz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.business.quartz.vo.JobVO;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @ClassName QuartzJobService.interface
 * @Author Xuhui
 * @Date 2022/7/15 17:16
 * @Description
 */
public interface QuartzJobService {

    List<JobVO> listJobs(QueryWrapper queryWrapper);

    void stopJob(JobVO jobVO) throws SchedulerException;

    void startJob(JobVO jobVO) throws SchedulerException, ClassNotFoundException;
}
