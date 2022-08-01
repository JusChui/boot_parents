package com.boot.business.quartz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.mapper.QuartzMapper;
import com.boot.business.quartz.service.QuartzService;
import com.boot.business.quartz.vo.QuartzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuartzServiceImpl.java
 * @Author Xuhui
 * @Date 2022/8/1 14:50
 * @Description
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzMapper quartzMapper;

    @Override
    public Page<QuartzVo> getQuartzList(QuartzVo quartzVo, int page, int limit) {

        Page<QuartzVo> voPage = new Page<>(page, limit);
        List<QuartzVo> voList = quartzMapper.getQuartzList(quartzVo, voPage);
        voPage.setRecords(voList);
        return voPage;
    }
}
