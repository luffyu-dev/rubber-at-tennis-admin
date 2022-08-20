package com.rubber.at.tennis.admin.dao.dal.impl;

import com.rubber.at.tennis.admin.dao.entity.TaskInfoEntity;
import com.rubber.at.tennis.admin.dao.mapper.TaskInfoMapper;
import com.rubber.at.tennis.admin.dao.dal.ITaskInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务记录表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
@Service
public class TaskInfoDalImpl extends BaseAdminService<TaskInfoMapper, TaskInfoEntity> implements ITaskInfoDal {

}
