package com.yonyou.cloud.quartz.service;

import com.yonyou.cloud.quartz.entity.ScheduleEntity;
import com.yonyou.cloud.quartz.entity.dto.PauseJobParamsDto;
import com.yonyou.cloud.quartz.entity.dto.ResumeJobParamsDto;
import com.yonyou.cloud.quartz.entity.dto.TriggerJobParamsDto;
import com.yonyou.cloud.quartz.jobs.ScheduledJob;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * @author daniell
 */
@Log4j2
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Resource
    private Scheduler scheduler;

    /**
     * 增加定时任务
     *
     * @param scheduleJobEntity
     * @return
     */
    @Override
    public boolean insertScheduleJob(ScheduleEntity scheduleJobEntity) {
        try {
            JobBuilder jobBuilder = JobBuilder.newJob(ScheduledJob.class)
                    .withIdentity(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup())
                    .usingJobData("jobGroup", scheduleJobEntity.getJobGroup())
                    .usingJobData("jobName", scheduleJobEntity.getJobName());
            JobDetail jobDetail = jobBuilder.build();
            jobDetail.getJobDataMap().put("scheduleJobEntity", scheduleJobEntity);
            // 表达式调度构建器（可判断创建SimpleScheduleBuilder）
            CronScheduleBuilder scheduleBuilder =
                    CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger =
                    TriggerBuilder.newTrigger()
                            .withIdentity(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup())
                            .withSchedule(scheduleBuilder)
                            .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("增加调度任务失败,异常信息={}", e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 暂停调度任务
     *
     * @param pauseJobParamsDto
     * @return
     */
    @Override
    public boolean pauseScheduleJob(PauseJobParamsDto pauseJobParamsDto) {
        JobKey key = new JobKey(pauseJobParamsDto.getJobName(), pauseJobParamsDto.getJobGroup());
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            log.error("暂停调度任务失败,异常信息={}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 恢复调度任务
     *
     * @param resumeJobParamsDto
     */
    @Override
    public boolean resumeScheduleJob(ResumeJobParamsDto resumeJobParamsDto) {
        JobKey key = new JobKey(resumeJobParamsDto.getJobName(), resumeJobParamsDto.getJobGroup());
        try {
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            log.error("恢复调度任务失败,异常信息={}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 触发当前调度任务
     *
     * @param triggerJobParamsDto
     * @return
     */
    @Override
    public boolean triggerScheduleJob(TriggerJobParamsDto triggerJobParamsDto) {
        JobKey jobKey = JobKey.jobKey(triggerJobParamsDto.getJobName(), triggerJobParamsDto.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("触发当前调度任务失败,异常信息={}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除调度任务
     *
     * @param scheduleEntity
     * @return
     */
    @Override
    public boolean deleteScheduleJob(ScheduleEntity scheduleEntity) {
        JobKey key = new JobKey(scheduleEntity.getJobName(), scheduleEntity.getJobGroup());
        try {
            scheduler.deleteJob(key);
        } catch (SchedulerException e) {
            log.error("删除调度任务失败,异常信息={}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新调度任务
     *
     * @param scheduleJobEntity
     */
    @Override
    public boolean updateScheduleJob(ScheduleEntity scheduleJobEntity) {
        TriggerKey triggerKey =
                TriggerKey.triggerKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        JobKey jobKey = new JobKey(scheduleJobEntity.getJobName(), scheduleJobEntity.getJobGroup());
        CronScheduleBuilder cronScheduleBuilder =
                CronScheduleBuilder.cronSchedule(scheduleJobEntity.getCronExpression())
                        .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withDescription(scheduleJobEntity.getDescription())
                        .withSchedule(cronScheduleBuilder)
                        .build();
        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(scheduleJobEntity.getDescription()).build();
            jobDetail.getJobDataMap().put("jobGroup", scheduleJobEntity.getJobGroup());
            jobDetail.getJobDataMap().put("jobName", scheduleJobEntity.getJobName());
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            log.error("删除调度任务失败,异常信息={}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
