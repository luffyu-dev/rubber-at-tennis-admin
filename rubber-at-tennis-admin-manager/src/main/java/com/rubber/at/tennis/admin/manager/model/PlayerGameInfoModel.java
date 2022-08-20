package com.rubber.at.tennis.admin.manager.model;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2022/5/28
 */
@Data
public class PlayerGameInfoModel {

    /**
     * 用户头像
     */
    private String userAvatar;


    /**
     * 积分组成的详情
     */
    private List<MatchPointInfo> matchPointInfoList;


}
