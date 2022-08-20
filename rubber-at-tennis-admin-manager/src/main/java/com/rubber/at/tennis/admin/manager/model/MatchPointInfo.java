package com.rubber.at.tennis.admin.manager.model;

import lombok.Data;

import java.util.List;

/**
 * @author luffyu
 * Created on 2022/5/28
 */
@Data
public class MatchPointInfo {

    /**
     * 比赛类型名称
     */
    private String matchTypeName;

    /**
     * 比赛具体信息
     */
    private List<MatchPointBean> matchPointList;



    @Data
    public static class MatchPointBean {

        /**
         * 比赛年份
         */
        private String matchYear;

        /**
         * 赛事名称
         */
        private String matchName;

        /**
         * 赛事所得积分
         */
        private Integer matchPoint;

        /**
         * 比赛结果
         */
        private String matchResult;

    }
}
