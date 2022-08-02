package com.boot.business.quartz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.business.quartz.vo.QuartzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName QuartzMapper.interface
 * @Author Xuhui
 * @Date 2022/8/1 14:36
 * @Description
 */
public interface QuartzMapper {

    List<QuartzVo> getQuartzJobs(@Param("vo") QuartzVo quartzVo);

    List<QuartzVo> getQuartzList(@Param("vo") QuartzVo quartzVo, @Param("page") Page<QuartzVo> voPage);

    void insertOrUpdateJob(@Param("vo") QuartzVo quartzVo);

    QuartzVo getQuartzById(@Param("jobId") String quartzJobId);

    void updateQuartzJobById(@Param("vo") QuartzVo quartz);
}
