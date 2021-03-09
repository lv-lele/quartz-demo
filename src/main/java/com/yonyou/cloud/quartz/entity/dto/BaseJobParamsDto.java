package com.yonyou.cloud.quartz.entity.dto;

import lombok.Data;

/**
 * @ClassName BaseJobParamsDto
 * @Description Job业务参数的公共属性类
 * @Author scott
 * @Date 2020/11/23 11:09 上午
 * @Version 1.0
 **/
@Data
public abstract class BaseJobParamsDto {
    /**
     * 调度任务名称
     */
    private String jobName;
    /**
     * 调度任务组
     */
    private String jobGroup;
}
