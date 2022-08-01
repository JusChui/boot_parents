package com.boot.business.quartz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.service.QuartzService;
import com.boot.business.quartz.vo.QuartzVo;
import com.boot.util.JSONResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
}
