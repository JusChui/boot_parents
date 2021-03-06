package com.boot.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Timestamp;

/**
 * @File: TestJob.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 20:30:20
 * @Description:
 */
@Slf4j
public class TestJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("{}--->test job logging...", new Timestamp(System.currentTimeMillis()));
    }
}
