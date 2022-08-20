package com.rubber.at.tennis.admin.dao.dal;

import com.rubber.at.tennis.admin.dao.entity.PlayerInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.IBaseAdminService;

/**
 * <p>
 * ATP运动员详情表 服务类
 * </p>
 *
 * @author rockyu
 * @since 2022-08-17
 */
public interface IPlayerInfoDal extends IBaseAdminService<PlayerInfoEntity> {

    /**
     * 通过第三方的业务id来查询
     * @param thirdId 第三方的业务id
     * @return 返回结果
     */
    PlayerInfoEntity getByPlayerId(String thirdId);

}
