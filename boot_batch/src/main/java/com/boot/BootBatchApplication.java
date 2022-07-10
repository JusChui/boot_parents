package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @File: BootBatchApplication.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 11:50:48
 * @Description:
 */
@SpringBootApplication
@MapperScan({"com.boot.**.mapper", "com.boot.**.dao"})
public class BootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBatchApplication.class, args);
    }
}
