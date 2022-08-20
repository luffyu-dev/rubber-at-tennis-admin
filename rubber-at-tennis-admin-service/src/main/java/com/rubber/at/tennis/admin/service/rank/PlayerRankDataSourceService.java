package com.rubber.at.tennis.admin.service.rank;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.rubber.at.tennis.admin.dao.dal.IPlayerRankInfoDal;

import com.rubber.at.tennis.admin.dao.entity.PlayerRankInfoEntity;
import com.rubber.at.tennis.admin.manager.model.ReptileModel;
import com.rubber.at.tennis.admin.manager.reptile.AtpRankReptile;
import com.rubber.at.tennis.admin.manager.reptile.WtaRankReptile;
import com.rubber.at.tennis.admin.service.task.TaskInfoService;
import com.rubber.at.tennis.admin.service.utils.NumUtils;
import com.rubber.at.tennis.map.api.player.enums.PlayerTypeEnums;
import com.rubber.at.tennis.map.api.task.TaskTypeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luffyu
 * Created on 2022/5/20
 */
@Slf4j
@Service
public class PlayerRankDataSourceService {

    @Autowired
    private IPlayerRankInfoDal iPlayerRankInfoDal;

    /**
     * 一次处理的数据长度
     */
    private static final Integer HANDLER_DATA_SIZE = 100;

    private static final String DATE_VERSION_FORMAT = "yyyyMMdd";

    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 任务循环执行
     */
    public void atpStartRankReptileTask(){
        Date now = new Date();
        String format = DateUtil.format(now, DATE_VERSION_FORMAT);
        int taskId = taskInfoService.initTaskInfo(TaskTypeEnums.ATP_RANK,format);
        int i=0;
        while (!rankReptileSaveData(now, i * HANDLER_DATA_SIZE, HANDLER_DATA_SIZE,PlayerTypeEnums.atp)) {
            i++;
        }
        taskInfoService.finishTaskInfo(taskId);

    }



    /**
     * 任务循环执行
     */
    public void wtaStartRankReptileTask(){
        Date now = new Date();
        String format = DateUtil.format(now, DATE_VERSION_FORMAT);
        int taskId = taskInfoService.initTaskInfo(TaskTypeEnums.WTA_RANK,format);
        int i=0;
        while (!rankReptileSaveData(now, i * HANDLER_DATA_SIZE, HANDLER_DATA_SIZE,PlayerTypeEnums.wta)) {
            i++;
        }
        taskInfoService.finishTaskInfo(taskId);
    }


    /**
     * 操作排名信息到数据库
     * @param index 当前的index对象
     * @param size 当时的长度
     */
    public boolean rankReptileSaveData(Date dateTime, int index, int size, PlayerTypeEnums playerTypeEnums){
        log.info("开始查询排名数据-index:{}-size:{}",index,size);
        ReptileModel model = new ReptileModel();
        model.setIndex(index);
        model.setSize(size);
        if (PlayerTypeEnums.atp.equals(playerTypeEnums)){
            AtpRankReptile.pageAtpRank(model);
        }else {
            WtaRankReptile.pageWtaRank(model);
        }

        if (!model.isRequestSuccess()){
            log.error("查询数据失败-{}-{}",model.getIndex(),model.getSize());
            return false;
        }
        log.info("开始查询数据-{}-{}，总数-{}",model.getIndex(),model.getSize(),model.getTotal());
        if (model.getTotal() <  index + size){
            return true;
        }
        List<PlayerRankInfoEntity> rankAddList = new ArrayList<>();
        List<PlayerRankInfoEntity> rankUpdateList = new ArrayList<>();
        for (Object o:model.getData()){
            PlayerRankInfoEntity rankInfoEntity = doConvertEntity((JSONObject)o,playerTypeEnums);
            initNewPlayer(dateTime,rankInfoEntity);
            PlayerRankInfoEntity toDayOldRanInfo = iPlayerRankInfoDal.getByPlayerId(rankInfoEntity.getPlayerId(),rankInfoEntity.getDateVersion());
            if (toDayOldRanInfo == null){
                rankAddList.add(rankInfoEntity);
            }else {
                BeanUtils.copyProperties(rankInfoEntity,toDayOldRanInfo,"id","createTime","version");
                rankUpdateList.add(toDayOldRanInfo);
            }
        }
        log.info("开始写入排名数据到数据库-index:{}-size:{}",index,size);
        if (CollUtil.isNotEmpty(rankAddList)){
            iPlayerRankInfoDal.saveBatch(rankAddList);
        }
        if (CollUtil.isNotEmpty(rankUpdateList)){
            iPlayerRankInfoDal.updateBatchById(rankUpdateList);
        }
        return false;
    }


    /**
     * 新增初始化
     */
    private void initNewPlayer(Date dateTime, PlayerRankInfoEntity data){
        data.setDateVersion(DateUtil.format(dateTime,DATE_VERSION_FORMAT));
        data.setCreateTime(dateTime);
        data.setUpdateTime(dateTime);
        data.setVersion(0);
        data.setStatus(1);
    }


    /**
     * 数据转换
     * @param object 当前的参数
     * @return 返回数据对象
     */
    private PlayerRankInfoEntity doConvertEntity(JSONObject object,PlayerTypeEnums playerTypeEnums){
        PlayerRankInfoEntity rankInfoEntity = new PlayerRankInfoEntity();
        rankInfoEntity.setPlayerId(object.getString("id"));
        rankInfoEntity.setThirdId(object.getString("id"));
        rankInfoEntity.setPlayerType(playerTypeEnums.toString());

        String fullName = object.getString("full_name");
        String chinaFullName = fullName;
        if (chinaFullName.contains("/>")){
            chinaFullName = fullName.substring(fullName.indexOf("/>") + 2);
        }
        if (fullName.contains("http")){
            String nationImg = fullName.substring(fullName.indexOf("http"), fullName.lastIndexOf("\""));
            rankInfoEntity.setNationImg(nationImg);
        }
        rankInfoEntity.setChinaFullName(chinaFullName);
        rankInfoEntity.setNationChineseName(object.getString("nation"));

        //积分
        rankInfoEntity.setPoint(NumUtils.changeToNum(object.getString("point")));
        rankInfoEntity.setFlopPoint(NumUtils.changeToNum(object.getString("flop")));
        rankInfoEntity.setWinPoint(NumUtils.changeToNum(object.getString("w_point")));

        //排名
        rankInfoEntity.setRankChange(NumUtils.changeToNum(object.getString("change")));
        rankInfoEntity.setRank(NumUtils.changeToNum(object.getString("c_rank")));
        rankInfoEntity.setOfficialRank(NumUtils.changeToNum(object.getString("f_rank")));
        rankInfoEntity.setHighestRank(NumUtils.changeToNum(object.getString("highest")));

        //周期
        rankInfoEntity.setCycleBonus(object.getString("prize"));
        rankInfoEntity.setCycleLoseNum(NumUtils.changeToNum(object.getString("lose")));
        rankInfoEntity.setCycleWinNum(NumUtils.changeToNum(object.getString("win")));
        rankInfoEntity.setCycleWinPro(object.getString("win_r"));

        return rankInfoEntity;
    }




}
