package com.boot.config.quartz;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @File: QuartzConfig.java
 * @Author: JusChui
 * @CreateTime: 2022-08-02 21:22:51
 * @Description:
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Bean
    public JobFactory jobFactory() {
        return new AdaptableJobFactory() {
            @Override
            protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
                Object object = super.createJobInstance(bundle);
                autowireCapableBeanFactory.autowireBean(object);
                return object;
            }
        };
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setOverwriteExistingJobs(true);
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            //设置延迟启动
            schedulerFactoryBean.setStartupDelay(1);
            schedulerFactoryBean.setJobFactory(jobFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }

    // 指定quartz.properties，可在配置文件中配置相关属性
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    // 创建schedule
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}
