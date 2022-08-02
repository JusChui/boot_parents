package com.boot.business.quartz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.vo.QuartzVo;
import org.quartz.SchedulerException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName QuartzService.interface
 * @Author Xuhui
 * @Date 2022/8/1 14:36
 * @Description
 */
public interface QuartzService {

    /**
     * 分页条件查询任务列表
     *
     * @param quartzVo
     * @param page
     * @param limit
     * @return
     */
    Page<QuartzVo> getQuartzList(QuartzVo quartzVo, int page, int limit);


    /**
     * 条件查询任务列表
     *
     * @param quartzVo
     * @return
     */
    List<QuartzVo> getQuartzList(QuartzVo quartzVo);

    /**
     * 新增任务
     *
     * @param quartzVo
     */
    void addQuartzJob(QuartzVo quartzVo);

    /**
     * 开启任务
     *
     * @param quartzVo
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    void startJob(QuartzVo quartzVo) throws ClassNotFoundException, SchedulerException;

    /**
     * 切换任务状态
     *
     * @param vo
     */
    void switchJobStatus(QuartzVo vo) throws SchedulerException, ClassNotFoundException;

    /**
     * 立即启动一次
     *
     * @param vo
     * @throws SchedulerException
     */
    void startOnce(QuartzVo vo) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, SchedulerException;
}
