package com.rubber.at.tennis.admin.service.controller;

import com.rubber.at.tennis.admin.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.admin.dao.entity.PlayerRankInfoEntity;
import com.rubber.base.components.mysql.plugins.admin.BaseAdminController;
import com.rubber.base.components.mysql.plugins.admin.page.PageModel;
import com.rubber.base.components.mysql.plugins.admin.page.SortType;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luffyu
 * Created on 2022/8/27
 */
@RequestMapping("/rank")
@RestController
public class RankQueryController extends BaseAdminController {



    @Autowired
    private IPlayerRankInfoDal iPlayerRankInfoDal;

    /**
     * 分页查询
     * @param json 部门的查询信息的json字符串
     * @return 返回查询的信息
     */
    @GetMapping("/list")
    public ResultMsg list(String json){
        PageModel pageModel = decodeForJsonString(json);
        if (pageModel.getOrder() == null){
            pageModel.setSort(new String[]{"rank"});
            pageModel.setOrder(SortType.desc);
        }
        return ResultMsg.success(iPlayerRankInfoDal.pageBySelect(pageModel, PlayerRankInfoEntity.class, null));
    }



}
