package com.boot.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestQuartzJob.java
 * @Author Xuhui
 * @Date 2022/8/2 12:38
 * @Description
 */
@Component(value = "testQuartzJob")
@Slf4j
public class TestQuartzJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        String param = mergedJobDataMap.getString("param");
        log.info("sssssssss---{}", param);
    }
}
