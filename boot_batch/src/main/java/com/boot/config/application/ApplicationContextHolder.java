package com.boot.config.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @File: ApplicationContexHolder.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 17:32:49
 * @Description:
 */
@Component
@Slf4j
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("start initialize applicationContext...");
        this.applicationContext = applicationContext;
        log.info("successfully initialized applicationContext!!!");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
