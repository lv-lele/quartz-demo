package com.yonyou.cloud.quartz.jobs;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.io.Serializable;

/**
 * 执行调度
 *
 * @author BENJAMIN
 */
@Log4j2
public class ScheduledJob implements Job, Serializable {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 阻塞40s
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobGroup = dataMap.getString("jobGroup");
        String jobName = dataMap.getString("jobName");
        String cronExpression = dataMap.getString("cronExpression");
        log.info("jobName = {} ---- jobGroup ={} ---- cronExpression = {}", jobName, jobGroup, cronExpression);
    }

}
