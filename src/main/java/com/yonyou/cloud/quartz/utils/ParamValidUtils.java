package com.yonyou.cloud.quartz.utils;

import com.alibaba.fastjson.JSON;
import org.quartz.CronExpression;

/**
 * @ClassName ParamValidUtils
 * @Description 参数校验工具类
 * @Author scott
 * @Date 2020/11/20 5:19 下午
 * @Version 1.0
 **/
public class ParamValidUtils {
    /**
     * corn 表达式校验
     * @param cronExpression
     * @return
     */
    public static boolean isCronExpression(String cronExpression){
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * 字符串 json格式校验
     * @param param
     * @return
     */
    public static boolean isJSONString(String param) {
        boolean result = true;
        try {
            JSON.parse(param);
        } catch (Exception e) {
            result=false;
        }
        return result;
    }
}
