package com.boot.business.quartz.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.business.quartz.vo.JobVO;

/**
 * @ClassName QuartzJobMapper.interface
 * @Author Xuhui
 * @Date 2022/7/15 17:16
 * @Description
 */
@DS("quartz")
public interface QuartzJobMapper extends BaseMapper<JobVO> {
}
