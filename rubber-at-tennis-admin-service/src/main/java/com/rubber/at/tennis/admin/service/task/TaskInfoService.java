package com.rubber.at.tennis.admin.service.task;


import com.rubber.at.tennis.admin.dao.dal.ITaskInfoDal;
import com.rubber.at.tennis.admin.dao.entity.TaskInfoEntity;
import com.rubber.at.tennis.atp.api.task.TaskTypeEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * @author luffyu
 * Created on 2022/5/28
 */
@Service
public class TaskInfoService {

    @Autowired
    private ITaskInfoDal iTaskInfoDal;



    public Integer initTaskInfo(TaskTypeEnums taskTypeEnums, String dataVersion){
        TaskInfoEntity taskInfo = new TaskInfoEntity();
        taskInfo.setTaskType(taskTypeEnums.toString());
        taskInfo.setStatus(0);
        taskInfo.setVersion(1);
        taskInfo.setCreateTime(new Date());
        taskInfo.setUpdateTime(new Date());
        taskInfo.setDataVersion(dataVersion);
        iTaskInfoDal.save(taskInfo);
        return taskInfo.getId();
    }


    public Integer initTaskInfo(TaskTypeEnums taskTypeEnums){
        TaskInfoEntity taskInfo = new TaskInfoEntity();
        taskInfo.setTaskType(taskTypeEnums.toString());
        taskInfo.setStatus(0);
        taskInfo.setVersion(1);
        taskInfo.setCreateTime(new Date());
        taskInfo.setUpdateTime(new Date());
        iTaskInfoDal.save(taskInfo);
        return taskInfo.getId();
    }


    /**
     * 任务完成
     * @param id 当前的id
     */
    public void finishTaskInfo(Integer id){
        TaskInfoEntity taskInfo = iTaskInfoDal.getById(id);
        taskInfo.setStatus(1);
        taskInfo.setUpdateTime(new Date());
        iTaskInfoDal.updateById(taskInfo);
    }
}
