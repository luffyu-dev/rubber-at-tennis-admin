package com.rubber.at.tennis.admin.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rubber.at.tennis.admin.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.admin.dao.mapper.PlayerInfoMapper;
import com.rubber.at.tennis.admin.dao.dal.IPlayerInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ATP运动员详情表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
@Service
public class PlayerInfoDalImpl extends BaseAdminService<PlayerInfoMapper, PlayerInfoEntity> implements IPlayerInfoDal {

    /**
     * 通过第三方的业务id来查询
     *
     * @param thirdId 第三方的业务id
     * @return 返回结果
     */
    @Override
    public PlayerInfoEntity getByPlayerId(String thirdId) {
        LambdaQueryWrapper<PlayerInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerInfoEntity::getPlayerId,thirdId);
        return getOne(lqw);
    }
}
