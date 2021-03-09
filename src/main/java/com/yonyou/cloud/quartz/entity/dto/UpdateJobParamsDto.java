package com.yonyou.cloud.quartz.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName UpdateJobParamsDto
 * @Description TODO
 * @Author scott
 * @Date 2020/11/23 10:14 上午
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class UpdateJobParamsDto extends BaseJobParamsDto {
    /**
     * cron表达式
     */
    private String cronExpression;
}
