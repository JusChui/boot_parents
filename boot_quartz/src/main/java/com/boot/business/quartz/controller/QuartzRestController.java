package com.boot.business.quartz.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.service.QuartzService;
import com.boot.business.quartz.vo.QuartzVo;
import com.boot.util.JSONResponse;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName QuartzRestController.java
 * @Author Xuhui
 * @Date 2022/8/1 14:16
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("quartz")
public class QuartzRestController {

    @Autowired
    private QuartzService quartzService;

    @RequestMapping("switchJobStatus")
    public JSONResponse switchJobStatus(QuartzVo vo) throws SchedulerException, ClassNotFoundException {
        log.info("切换任务状态---{}", vo);
        quartzService.switchJobStatus(vo);
        return new JSONResponse(200, "SUCCESS", null);
    }

    /**
     * 新增任务
     *
     * @param quartzVo
     * @return
     */
    @PostMapping("addQuartzJob")
    public JSONResponse addQuartzJob(QuartzVo quartzVo) {

        quartzVo.setCreateTime(new Date());
        quartzVo.setUpdateTime(new Date());
        quartzVo.setDeleteFlag("00");

        quartzService.addQuartzJob(quartzVo);
        return new JSONResponse(200, "SUCCESS", null);
    }

    /**
     * 获取数据库配置的任务列表
     *
     * @param quartzVo
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("quartzList")
    public JSONResponse getQuartzJobList(QuartzVo quartzVo,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("limit") Integer limit) {
        log.info("/quartz/quartzList参数-->{},,page={}, limit={}", quartzVo, page, limit);
        return new JSONResponse(200, "SUCCESS",
                quartzService.getQuartzList(quartzVo, page, limit));

    }

    /**
     * 任务管理页面
     *
     * @return
     */
    @GetMapping("quartz.html")
    public ModelAndView quartzHtml() {
        return new ModelAndView("quartz/quartz.html");
    }

    @RequestMapping("startJobs")
    public JSONResponse startJobs(@RequestBody String json) throws SchedulerException, ClassNotFoundException {
        log.info("开启任务--{}", json);
        List<QuartzVo> voList = JSONUtil.toList(json, QuartzVo.class);
        for (QuartzVo vo : voList) {
            vo.setQuartzJobStatus("1");
            quartzService.switchJobStatus(vo);
        }
        return new JSONResponse(200, "SUCCESS", null);
    }

    @RequestMapping("stopJobs")
    public JSONResponse stopJobs(@RequestBody String json) throws SchedulerException, ClassNotFoundException {
        log.info("开启任务--{}", json);
        List<QuartzVo> voList = JSONUtil.toList(json, QuartzVo.class);
        for (QuartzVo vo : voList) {
            vo.setQuartzJobStatus("0");
            quartzService.switchJobStatus(vo);
        }
        return new JSONResponse(200, "SUCCESS", null);
    }

    @RequestMapping("startOnce")
    public JSONResponse startOnce(@RequestBody String json) throws
            ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, SchedulerException {
        log.info("执行一次--{}", json);
        List<QuartzVo> voList = JSONUtil.toList(json, QuartzVo.class);
        for (QuartzVo vo : voList) {
            //vo.setQuartzJobStatus("0");
            quartzService.startOnce(vo);
        }
        return new JSONResponse(200, "SUCCESS", null);
    }
}
