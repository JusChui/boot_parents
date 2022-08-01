package com.boot.business.quartz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.vo.QuartzVo;

/**
 * @ClassName QuartzService.interface
 * @Author Xuhui
 * @Date 2022/8/1 14:36
 * @Description
 */
public interface QuartzService {

    Page<QuartzVo> getQuartzList(QuartzVo quartzVo, int page, int limit);
}
