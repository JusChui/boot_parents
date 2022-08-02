package com.boot.config.quartz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationContextHolder.java
 * @Author Xuhui
 * @Date 2022/8/2 13:34
 * @Description
 */
@Component
public class ApplicationContextHolder<T> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public T getBean(String beanName) {
        return (T) this.applicationContext.getBean(beanName);
    }

    public T getBeanByClass(String aCls) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(aCls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) this.applicationContext.getBean(aClass);
    }
}
