package com.boot.config.application;

import com.boot.task.TestJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(ApplicationArguments args) {
        try {
            JobDetail detail =
                    JobBuilder.newJob(TestJob.class)
                            //.withIdentity("com.")
                            .build();
            CronTrigger trigger =
                    TriggerBuilder.newTrigger()
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * ? * *"))
                            .build();
            scheduler.start();
            scheduler.scheduleJob(detail, trigger);
        } catch (Exception e) {
            log.error("创建任务失败--{}", e);
        }
    }
}
