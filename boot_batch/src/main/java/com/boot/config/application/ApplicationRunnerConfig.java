package com.boot.config.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.business.quartz.service.QuartzJobService;
import com.boot.business.quartz.vo.JobVO;
import com.boot.task.TestJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @File: ApplicationRunnerConfig.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 17:28:57
 * @Description:
 */
@Component
@Slf4j
public class ApplicationRunnerConfig implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 测试任务
     *
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {
        //log.info("execute code after launch:");
        try {
            QueryWrapper<JobVO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("DELETE_FLAG", "00");
            List<JobVO> jobVOS = quartzJobService.listJobs(queryWrapper);   //加载数据库的任务
            if (!CollectionUtils.isEmpty(jobVOS)) {
                //遍历任务列表
                for (JobVO jobVO : jobVOS) {
                    if (StringUtils.equals("1", jobVO.getJobStatus())) {
                        quartzJobService.startJob(jobVO);
                        log.info("任务启动成功-->{}！！", jobVO);
                    }
                }
            }
        } catch (Exception e) {
            log.error("创建任务失败--{}", e);
        }
    }
}
