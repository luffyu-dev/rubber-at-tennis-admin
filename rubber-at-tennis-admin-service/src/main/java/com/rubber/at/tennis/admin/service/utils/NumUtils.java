package com.rubber.at.tennis.admin.service.utils;

import cn.hutool.core.util.NumberUtil;

/**
 * @author luffyu
 * Created on 2022/5/20
 */
public class NumUtils extends NumberUtil {


    /**
     * 变更图number
     * @param value
     * @return
     */
    public static Integer changeToNum(String value){
        if (isNumber(value)){
            return Integer.valueOf(value);
        }
        return null;
    }

}
