package com.yonyou.cloud.quartz.service;

import com.yonyou.cloud.quartz.entity.ScheduleEntity;
import com.yonyou.cloud.quartz.entity.dto.PauseJobParamsDto;
import com.yonyou.cloud.quartz.entity.dto.ResumeJobParamsDto;
import com.yonyou.cloud.quartz.entity.dto.TriggerJobParamsDto;

/**
 * @ClassName ScheduleJobService
 * @Description 调度任务执行接口
 * @Author scott
 * @Date 2020/11/20 6:05 下午
 * @Version 1.0
 **/
public interface ScheduleJobService {
    /**
     * 增加调度任务
     *
     * @param scheduleJobEntity
     * @return
     */
    public boolean insertScheduleJob(ScheduleEntity scheduleJobEntity);

    /**
     * 停止调度任务
     *
     * @param pauseJobParamsDto
     * @return
     */
    public boolean pauseScheduleJob(PauseJobParamsDto pauseJobParamsDto);

    /**
     * 恢复调度任务
     *
     * @param resumeJobParamsDto
     * @return
     */
    public boolean resumeScheduleJob(ResumeJobParamsDto resumeJobParamsDto);

    /**
     * 触发当前调度任务
     * @param triggerJobParamsDto
     * @return
     */
    boolean triggerScheduleJob(TriggerJobParamsDto triggerJobParamsDto);

    /**
     * 删除调度任务
     *
     * @param scheduleEntity
     * @return
     */
    public boolean deleteScheduleJob(ScheduleEntity scheduleEntity);

    /**
     * 更新调度任务
     *
     * @param scheduleJobEntity
     * @return
     */
    public boolean updateScheduleJob(ScheduleEntity scheduleJobEntity);

}
