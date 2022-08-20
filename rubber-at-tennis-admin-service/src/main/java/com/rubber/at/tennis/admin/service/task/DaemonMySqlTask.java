package com.rubber.at.tennis.admin.service.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.at.tennis.admin.dao.dal.IPlayerRankInfoDal;
import com.rubber.at.tennis.admin.dao.entity.PlayerRankInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luffyu
 * Created on 2022/5/24
 */
@Slf4j
@EnableScheduling
@Configuration
@Component
public class DaemonMySqlTask {

    @Autowired
    private IPlayerRankInfoDal iPlayerRankInfoDal;

    /**
     * 用于维护数据库的连接
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void doDaemonMySql(){
        try {
            log.info("start com.rubber.wx.at.atp.api.task.DaemonMySqlTask.doDaemonMySql");
            QueryWrapper<PlayerRankInfoEntity> qw = new QueryWrapper<>();
            qw.lambda().eq(PlayerRankInfoEntity::getId, 1);
            iPlayerRankInfoDal.list(qw);
        }catch (Exception e){
            log.error("start com.rubber.wx.at.atp.api.task.DaemonMySqlTask.doDaemonMySql msg={}",e.getMessage());
        }

    }
}
