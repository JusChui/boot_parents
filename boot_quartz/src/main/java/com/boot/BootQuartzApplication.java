package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName BootQuartzApplication.java
 * @Author Xuhui
 * @Date 2022/7/27 15:02
 * @Description
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootQuartzApplication.class);
    }
}
