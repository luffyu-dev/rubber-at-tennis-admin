package com.rubber.at.tennis.admin.service.player;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;

import com.rubber.at.tennis.admin.dao.dal.IPlayerInfoDal;
import com.rubber.at.tennis.admin.dao.entity.PlayerInfoEntity;
import com.rubber.at.tennis.admin.manager.model.ReptileModel;
import com.rubber.at.tennis.admin.manager.reptile.AtpPlayerReptile;
import com.rubber.at.tennis.admin.manager.reptile.WtaPlayerReptile;
import com.rubber.at.tennis.admin.service.task.TaskInfoService;
import com.rubber.at.tennis.admin.service.utils.NumUtils;
import com.rubber.at.tennis.atp.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.atp.api.task.TaskTypeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luffyu
 * Created on 2022/5/14
 */
@Slf4j
@Service
public class PlayerInfoDatasourceService {

    @Autowired
    private IPlayerInfoDal iPlayerInfoDal;

    @Autowired
    private TaskInfoService taskInfoService;


    /**
     * 一次处理的数据长度
     */
    private static final Integer HANDLER_DATA_SIZE = 100;




    public void startAtpPlayerInfoReptileTask()  {
        int taskId = taskInfoService.initTaskInfo(TaskTypeEnums.ATP_INFO);
        int i=0;
        while (!playerInfoReptileSaveData(i*HANDLER_DATA_SIZE,HANDLER_DATA_SIZE,PlayerTypeEnums.atp)){
            i++;
        }
        taskInfoService.finishTaskInfo(taskId);
    }


    public void startWtaPlayerInfoReptileTask()  {
        int taskId = taskInfoService.initTaskInfo(TaskTypeEnums.WTA_INFO);
        int i=0;
        while (!playerInfoReptileSaveData(i*HANDLER_DATA_SIZE,HANDLER_DATA_SIZE,PlayerTypeEnums.wta)){
            i++;
        }
        taskInfoService.finishTaskInfo(taskId);
    }


    /**
     * 操作球员信息到数据库
     * @param index 当前的index对象
     * @param size 当时的长度
     */
    public boolean playerInfoReptileSaveData(int index,int size,PlayerTypeEnums playerTypeEnums){
        ReptileModel model = new ReptileModel();
        model.setIndex(index);
        model.setSize(size);
        log.info("开始查询数据-{}-{}",model.getIndex(),model.getSize());
        if (PlayerTypeEnums.atp.equals(playerTypeEnums)){
            AtpPlayerReptile.queryPageAtpPlayer(model);
        }else {
            WtaPlayerReptile.queryPageWtaPlayer(model);
        }

        if (!model.isRequestSuccess()){
            log.info("查询数据失败-{}-{}",model.getIndex(),model.getSize());
            return false;
        }
        log.info("开始查询数据-{}-{}，总数-{}",model.getIndex(),model.getSize(),model.getTotal());
        if (model.getTotal() < model.getIndex()  + model.getSize()){
            return true;
        }
        List<PlayerInfoEntity> needAddList = new ArrayList<>();
        List<PlayerInfoEntity> needUpdateList = new ArrayList<>();

        for (Object o:model.getData()){
            PlayerInfoEntity  playerInfo = doConvertEntity((JSONObject)o,playerTypeEnums);
            if (playerInfo == null){
                continue;
            }
            PlayerInfoEntity oldPlayerInfo = iPlayerInfoDal.getByPlayerId(playerInfo.getPlayerId());
            if (oldPlayerInfo == null){
                initNewPlayer(playerInfo);
                needAddList.add(playerInfo);
            }else if (checkDiff(playerInfo,oldPlayerInfo)){
                oldPlayerInfo.setDoubleRankHeight(playerInfo.getDoubleRankHeight());
                needUpdateList.add(oldPlayerInfo);
            }
        }
        if (CollUtil.isNotEmpty(needAddList)){
            iPlayerInfoDal.saveBatch(needAddList);
        }
        if (CollUtil.isNotEmpty(needUpdateList)){
            iPlayerInfoDal.updateBatchById(needUpdateList);
        }
        return false;
    }



    private void initNewPlayer(PlayerInfoEntity data){
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        data.setVersion(0);
        data.setStatus(1);
    }


    private boolean checkDiff(PlayerInfoEntity newData,PlayerInfoEntity oldData){
        boolean diff = false;

        if (!oldData.getSinglesChampionNum().equals(newData.getSinglesChampionNum())){
            oldData.setSinglesChampionNum(newData.getSinglesChampionNum());
            diff = true;
        }
        if (!oldData.getSinglesRankHeight().equals(newData.getSinglesRankHeight())){
            oldData.setSinglesRankHeight(newData.getSinglesRankHeight());
            diff = true;
        }
        if (!oldData.getDoubleChampionNum().equals(newData.getDoubleChampionNum())){
            oldData.setDoubleChampionNum(newData.getDoubleChampionNum());
            diff = true;
        }
        if (!oldData.getDoubleRankHeight().equals(newData.getDoubleRankHeight())){
            oldData.setDoubleRankHeight(newData.getDoubleRankHeight());
            diff = true;
        }

        if (!oldData.getAllBonus().equals(newData.getAllBonus())){
            oldData.setAllBonus(newData.getAllBonus());
            diff = true;
        }
        if (diff){
            oldData.setCreateTime(new Date());
        }
        return diff;
    }


    /**
     * 数据对象的转换
     * @param object
     * @return
     */
    private PlayerInfoEntity doConvertEntity(JSONObject object, PlayerTypeEnums playerType){
        try {
            PlayerInfoEntity entity = new PlayerInfoEntity();
            entity.setThirdId(object.getString("id"));
            entity.setPlayerId(entity.getThirdId());
            // name: "<img class=cImgPlayerFlag data-original=\"https://www.rank-tennis.com/images/flag_svg/SRB.svg\" />诺瓦克·德约科维奇"
            String name = object.getString("name");

            String chinaFullName = name;
            if (chinaFullName.contains("/>")){
                chinaFullName = name.substring(name.indexOf("/>") + 2);
            }
            String chinaName = chinaFullName;
            if (chinaName.contains("·")) {
                chinaName = chinaFullName.substring(chinaFullName.indexOf("·") + 1);
            }
            if (name.contains("http")){
                String nationImg = name.substring(name.indexOf("http"), name.lastIndexOf("\""));
                entity.setNationImg(nationImg);
            }
            entity.setPlayerType(playerType.toString());
            entity.setChinaName(chinaName);
            entity.setChinaFullName(chinaFullName);
            entity.setFirstName(object.getString("first"));
            entity.setLastName(object.getString("last"));
            entity.setNationChineseName(object.getString("nationfull"));
            entity.setNationEnglishName("");
            // nationImage
            entity.setBirthday(object.getString("birthday"));
            entity.setBirthPlace(object.getString("birthplace"));
            entity.setResidence(object.getString("residence"));
            entity.setHeight(object.getString("height"));
            entity.setWeight(object.getString("weight"));
            entity.setProYear(object.getString("proyear"));

            entity.setAllBonus(object.getString("prize_c"));
            entity.setSinglesChampionNum(NumUtils.changeToNum(object.getString("title_s_c")));
            entity.setSinglesRankHeight(NumUtils.changeToNum(object.getString("rank_s_hi")));

            entity.setDoubleChampionNum(NumUtils.changeToNum(object.getString("title_d_c")));
            entity.setDoubleRankHeight(NumUtils.changeToNum(object.getString("rank_d_hi")));
            entity.setWebsite(object.getString("website"));
            return entity;
        }catch (Exception e){
            log.error("当前的数据对象转换失败，e={}",e.getMessage());
            return null;
        }
    }





}
