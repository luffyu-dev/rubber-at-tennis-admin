package com.rubber.at.tennis.admin.service.task;

import com.rubber.at.tennis.admin.service.player.PlayerInfoDatasourceService;
import com.rubber.at.tennis.admin.service.rank.PlayerRankDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luffyu
 * Created on 2022/5/28
 */
@Slf4j
@EnableScheduling
@Configuration
@Component
public class AutoTaskService {

    @Autowired
    private PlayerInfoDatasourceService playerInfoDatasourceService;

    @Autowired
    private PlayerRankDataSourceService playerRankDataSourceService;


    /**
     * 每周五的1点执行 自动更新atp球员信息
     */
    //@Scheduled(cron = "0 0 1 ? * 5")
    public void taskAtpInfo()  {
        playerInfoDatasourceService.startAtpPlayerInfoReptileTask();
    }


    /**
     * 每周五的2点执行 自动更新wta球员信息
     */
    // @Scheduled(cron = "0 0 2 ? * 5")
    public void taskWtaInfo(){
        playerInfoDatasourceService.startWtaPlayerInfoReptileTask();
    }


    /**
     * 每天6点自动刷新 atp的排名信息
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void taskAtpRank(){
        playerRankDataSourceService.atpStartRankReptileTask();
    }


    /**
     * 每天7点自动刷新 wta的排名信息
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public void taskWtaRank(){
        playerRankDataSourceService.wtaStartRankReptileTask();
    }


}
