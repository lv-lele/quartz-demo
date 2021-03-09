package com.yonyou.cloud.quartz.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 定时任务实体
 *
 * @author yang
 */
@Data
@Accessors(chain = true)
public class ScheduleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务名
     */
    private String jobName;
    /**
     * 任务组
     */
    private String jobGroup;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 状态
     */
    private String status;
    /**
     * 描述
     */
    private String description;
}