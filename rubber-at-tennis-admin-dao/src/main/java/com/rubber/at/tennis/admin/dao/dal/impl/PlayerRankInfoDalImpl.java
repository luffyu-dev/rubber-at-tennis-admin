package com.rubber.at.tennis.admin.dao.dal.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.at.tennis.admin.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.admin.dao.mapper.PlayerRankInfoMapper;
import com.rubber.at.tennis.admin.dao.dal.IPlayerRankInfoDal;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ATP球员的排名表 服务实现类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
@Service
public class PlayerRankInfoDalImpl extends BaseAdminService<PlayerRankInfoMapper, PlayerRankInfoEntity> implements IPlayerRankInfoDal {


    /**
     * 通过第三方的业务id来查询
     *
     * @param playerId    第三方的业务id
     * @param dateVersion
     * @return 返回结果
     */
    @Override
    public PlayerRankInfoEntity getByPlayerId(String playerId, String dateVersion) {
        LambdaQueryWrapper<PlayerRankInfoEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PlayerRankInfoEntity::getPlayerId,playerId)
                .eq(PlayerRankInfoEntity::getDateVersion,dateVersion);
        return getOne(lqw);
    }
}
