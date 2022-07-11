package com.boot.business.quartz.controller;

import com.boot.entity.JSONResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @File: QuartzJobController.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 17:44:46
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/job")
public class QuartzJobController {

    @RequestMapping(value = "/addJob", method = {RequestMethod.POST})
    public JSONResponse addJob() {
        log.info("add job...");
        //TODO 新增定时任务
        return JSONResponse.getSuccessResponse();
    }
}
