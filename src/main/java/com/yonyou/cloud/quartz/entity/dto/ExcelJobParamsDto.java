package com.yonyou.cloud.quartz.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName ExcelJobParamsDto
 * @Description TODO
 * @Author scott
 * @Date 2020/12/14 11:18 上午
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class ExcelJobParamsDto extends BaseJobParamsDto {
    /**
     * corn 表达式
     */
    private String cronExpression;
    /**
     * url
     */
    private String url;
}
