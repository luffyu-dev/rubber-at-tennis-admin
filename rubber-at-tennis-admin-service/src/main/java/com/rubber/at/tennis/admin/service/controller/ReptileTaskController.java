package com.rubber.at.tennis.admin.service.controller;

import com.rubber.at.tennis.admin.service.player.PlayerInfoDatasourceService;
import com.rubber.at.tennis.admin.service.rank.PlayerRankDataSourceService;
import com.rubber.base.components.util.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author luffyu
 * Created on 2022/5/28
 */
@RequestMapping("/reptile-task")
@RestController
public class ReptileTaskController {



    private static final ThreadPoolExecutor ex = new ThreadPoolExecutor(1,2,2L, TimeUnit.HOURS,new ArrayBlockingQueue<>(100));



    @Autowired
    private PlayerInfoDatasourceService playerInfoDatasourceService;

    @Autowired
    private PlayerRankDataSourceService playerRankDataSourceService;


    /**
     * 每周五的1点执行 自动更新atp球员信息
     */
    @PostMapping("/player/atp")
    public ResultMsg startAtpPlayerInfoReptileTask()  {
        ex.execute(()-> {
            playerInfoDatasourceService.startAtpPlayerInfoReptileTask();
        });
        return ResultMsg.success();
    }


    /**
     * 每周五的2点执行 自动更新wta球员信息
     */
    @PostMapping("/player/wta")
    public ResultMsg startWtaPlayerInfoReptileTask(){
        ex.execute(()-> {
            playerInfoDatasourceService.startWtaPlayerInfoReptileTask();
        });
        return ResultMsg.success();
    }


    /**
     * 每天6点自动刷新 atp的排名信息
     */
    @PostMapping("/rank/atp")
    public ResultMsg atpStartRankReptileTask(){
        ex.execute(()-> {
            playerRankDataSourceService.atpStartRankReptileTask();
        });
        return ResultMsg.success();
    }


    /**
     * 每天7点自动刷新 wta的排名信息
     */
    @PostMapping("/rank/wta")
    public ResultMsg wtaStartRankReptileTask(){
        ex.execute(()-> {
            playerRankDataSourceService.wtaStartRankReptileTask();
        });
        return ResultMsg.success();
    }










}
