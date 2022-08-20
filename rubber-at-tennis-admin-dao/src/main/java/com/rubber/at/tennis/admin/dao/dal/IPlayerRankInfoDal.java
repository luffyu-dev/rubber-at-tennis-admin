package com.rubber.at.tennis.admin.dao.dal;

import com.rubber.at.tennis.admin.dao.entity.PlayerRankInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

/**
 * <p>
 * ATP球员的排名表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
public interface IPlayerRankInfoDal extends IBaseAdminService<PlayerRankInfoEntity> {

    /**
     * 通过第三方的业务id来查询
     * @param playerId 第三方的业务id
     * @return 返回结果
     */
    PlayerRankInfoEntity getByPlayerId(String playerId,String dateVersion);
}
