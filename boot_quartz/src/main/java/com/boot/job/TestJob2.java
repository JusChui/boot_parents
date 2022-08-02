package com.boot.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName TestJob2.java
 * @Author Xuhui
 * @Date 2022/8/2 15:53
 * @Description
 */
@Component(value = "testJob2")
@Slf4j
public class TestJob2 extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("{}--->job2执行。。。",
                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
