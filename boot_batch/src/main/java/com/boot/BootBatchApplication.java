package com.boot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @File: BootBatchApplication.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 11:50:48
 * @Description:
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class BootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBatchApplication.class, args);
    }
}
