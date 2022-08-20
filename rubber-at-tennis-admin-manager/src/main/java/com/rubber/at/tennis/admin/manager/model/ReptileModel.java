package com.rubber.at.tennis.admin.manager.model;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * @author luffyu
 * Created on 2022/5/14
 */
@Data
public class ReptileModel {


    /**
     * 每页大小
     */
    private int size = 10;

    /**
     * 当前页
     */
    private int index = 0;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 请求是否成功
     */
    private boolean requestSuccess;


    /**
     * 数据结果
     */
    private String msg;

    /**
     * 数据
     */
    private JSONArray data;
}
