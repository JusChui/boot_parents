package com.boot.business.quartz.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName QuartzVo.java
 * @Author Xuhui
 * @Date 2022/8/1 14:37
 * @Description
 */
@Data
public class QuartzVo implements Serializable {

    private String quartzJobId;
    private String quartzJobName;
    private String quartzJobClass;
    private String quartzJobGroup;
    private String quartzJobCron;
    private String quartzJobStatus;
    private String quartzJobParams;
    private String quartzJobDescription;

    private String creater;
    private Date createTime;

    private String updater;
    private Date updateTime;

    private String deleteFlag;

    /**
     * 是否立即启动
     * 1，是
     */
    private String startAtNow;

}
