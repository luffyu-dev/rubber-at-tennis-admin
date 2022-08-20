package com.rubber.at.tennis.admin.dao.dal.impl;

import com.rubber.at.tennis.admin.dao.entity.PlayerMatchInfoEntity;
import com.rubber.at.tennis.admin.dao.mapper.PlayerMatchInfoMapper;
import com.rubber.at.tennis.admin.dao.dal.IPlayerMatchInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 球员的比赛记录表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
@Service
public class PlayerMatchInfoDalImpl extends BaseAdminService<PlayerMatchInfoMapper, PlayerMatchInfoEntity> implements IPlayerMatchInfoDal {

}
