package com.yonyou.cloud.quartz.controller;

import com.yonyou.cloud.quartz.entity.ScheduleEntity;
import com.yonyou.cloud.quartz.service.ScheduleJobService;
import com.yonyou.cloud.quartz.utils.ParamValidUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName QuartzDemoController
 * @Description TODO
 * @Author scott
 * @Date 2021/3/8 3:31 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("quartz")
public class QuartzDemoController {
    @Resource
    private ScheduleJobService scheduleJobService;

    @RequestMapping("insert")
    public String insertJob() {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        String cornExpression = "30 * * * * ? *";
        if (!ParamValidUtils.isCronExpression(cornExpression)) {
            return "corn表达式校验失败";
        }
        scheduleEntity.setJobGroup("test222")
                .setJobName("test222")
                .setCronExpression(cornExpression);
        scheduleJobService.insertScheduleJob(scheduleEntity);
        return "ok";
    }
}
