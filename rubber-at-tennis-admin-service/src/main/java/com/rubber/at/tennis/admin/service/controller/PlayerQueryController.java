package com.rubber.at.tennis.admin.service.controller;

import com.rubber.at.tennis.admin.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.admin.dao.entity.PlayerInfoEntity;
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
@RequestMapping("/player")
@RestController
public class PlayerQueryController extends BaseAdminController {



    @Autowired
    private IPlayerInfoDal iPlayerInfoDal;

    /**
     * 分页查询
     * @param json 部门的查询信息的json字符串
     * @return 返回查询的信息
     */
    @GetMapping("/list")
    public ResultMsg list(String json){
        PageModel pageModel = decodeForJsonString(json);
        if (pageModel.getOrder() == null){
            pageModel.setSort(new String[]{"seqWeight"});
            pageModel.setOrder(SortType.desc);
        }
        return ResultMsg.success(iPlayerInfoDal.pageBySelect(pageModel, PlayerInfoEntity.class, null));
    }



}
