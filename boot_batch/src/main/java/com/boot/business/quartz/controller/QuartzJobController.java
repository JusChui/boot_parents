package com.boot.business.quartz.controller;

import com.boot.business.quartz.service.QuartzJobService;
import com.boot.business.quartz.vo.JobVO;
import com.boot.entity.JSONResponse;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 新增任务
     *
     * @return
     */
    @RequestMapping(value = "/addJob", method = {RequestMethod.POST})
    public JSONResponse addJob() {
        log.info("add job...");
        //TODO 新增定时任务
        return JSONResponse.getSuccessResponse();
    }

    /**
     * 停止任务
     *
     * @param jobVO
     * @return
     * @throws SchedulerException
     */
    @RequestMapping(value = "/stopJob", method = {RequestMethod.POST})
    public JSONResponse stopJob(@RequestBody JobVO jobVO) throws SchedulerException {
        log.info("stop job...");
        quartzJobService.stopJob(jobVO);
        return JSONResponse.getSuccessResponse();
    }

    /**
     * 开始任务
     *
     * @param jobVO
     * @return
     */
    @RequestMapping(value = "/startJob", method = {RequestMethod.POST})
    public JSONResponse startJob(@RequestBody JobVO jobVO) throws SchedulerException, ClassNotFoundException {
        log.info("start job...");
        quartzJobService.startJob(jobVO);
        return JSONResponse.getSuccessResponse();
    }
}
