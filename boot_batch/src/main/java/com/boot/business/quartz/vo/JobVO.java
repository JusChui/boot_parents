package com.boot.business.quartz.vo;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName JobVO.java
 * @Author Xuhui
 * @Date 2022/7/15 17:03
 * @Description
 */
@Data
@TableName("t_quartz_job")
@DS("quartz")
public class JobVO implements Serializable {

    @TableField(value = "JOB_ID")
    @TableId
    private String jobId;

    @TableField(value = "JOB_NAME")
    private String jobName;

    @TableField(value = "CRON")
    private String cron;

    @TableField(value = "JOB_CLASS")
    private String jobClass;

    @TableField(value = "JOB_STATUS")
    private String jobStatus;

    @TableField(value = "JOB_GROUP")
    private String jobGroup;

    @TableField(value = "DELETE_FLAG")
    private String deleteFlag;

    @TableField(value = "JOB_DESCRIPTION")
    private String jobDescription;

    @TableField(value = "CREATE_TIME")
    private Timestamp createTime;

    @TableField(value = "UPDATE_TIME")
    private Timestamp updateTime;
}
