package com.rubber.at.tennis.admin.manager.reptile;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.rubber.at.tennis.admin.manager.model.MatchPointInfo;
import com.rubber.at.tennis.admin.manager.model.PlayerGameInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luffyu
 * Created on 2022/5/25
 */
@Slf4j
public class PlayerGameInfoReptile {


    /**
     * 数据域名
     */
    private static final String PLAYER_REPTILE_UTL = "https://www.rank-tennis.com/zh/breakdown/atp/s/year/query";


    private static final String PLAYER_REPTILE_UTL_WTA = "  https://www.rank-tennis.com/zh/breakdown/wta/s/year/query";

    /**
     * 超时时间
     */
    private static final int PLAYER_REPTILE_UTL_TIMEOUT = 20000;


    public static void main(String[] args) throws Exception {
        PlayerGameInfoModel mode = queryAndResolveForWta("230220");
        System.out.println(mode);
    }


    public static PlayerGameInfoModel queryAndResolve(String playId){
        try{
            String  xx = doPageAtpPlayer(playId);
            return resolveHtml(xx);
        }catch (Exception e){
            log.info("当前的信息失败，playId={} msg={}",playId,e.getMessage());
            return null;
        }
    }



    public static PlayerGameInfoModel queryAndResolveForWta(String playId){
        try{
            String  xx = doPageAtpPlayerForWta(playId);
            return resolveHtml(xx);
        }catch (Exception e){
            log.info("当前的信息失败，playId={} msg={}",playId,e.getMessage());
            return null;
        }
    }



    /**
     * 解析结果
     * @param html
     * @return
     * @throws Exception
     */
    private static PlayerGameInfoModel resolveHtml(String html) throws Exception {
        PlayerGameInfoModel playerGameInfoModel = new PlayerGameInfoModel();
        Document document = Jsoup.parse(html);
        // 用户的头像新
        playerGameInfoModel.setUserAvatar(resolveUserAvatar(document));
        // 解析比赛
        playerGameInfoModel.setMatchPointInfoList(resolveMatchPointInfo(document));
        // 解析比赛详情
        resolveScript(document);
        return playerGameInfoModel;
    }


    /**
     * 解析图片
     */
    private static String resolveUserAvatar(Document document){
        Element userHead = document.getElementById("iBreakdownHead");
        String backImage = userHead.attr("style");
        return backImage.substring(backImage.indexOf("http"),backImage.indexOf(")"));
    }


    /**
     * 解析比赛详情
     */
    private static List<MatchPointInfo> resolveMatchPointInfo(Document document){
        Element tableDiv = document.getElementById("iBreakdownContentLevelTable");
        Element  table = tableDiv.getElementsByTag("tbody").get(0);
        Elements elements = table.getElementsByTag("tr");

        List<MatchPointInfo> result = new ArrayList<>();

        MatchPointInfo matchPointInfo = new MatchPointInfo();

        List<MatchPointInfo.MatchPointBean> pointBean = new ArrayList<>();

        for (Element tr:elements){
            Elements tds = tr.getElementsByTag("td");
            if (tds.size() == 1){
                matchPointInfo = new MatchPointInfo();
                pointBean = new ArrayList<>();
                String matchTypeName = tds.get(0).text();
                matchPointInfo.setMatchTypeName(matchTypeName);
                matchPointInfo.setMatchPointList(pointBean);
                result.add(matchPointInfo);
            }else if (tds.size() == 3){
                MatchPointInfo.MatchPointBean matchPointBean = new MatchPointInfo.MatchPointBean();
                String matchYearName = tds.get(0).text();
                String[] matchYearArray = matchYearName.split(" ");
                matchPointBean.setMatchYear(matchYearArray[0]);
                matchPointBean.setMatchName(matchYearArray[1]);
                matchPointBean.setMatchPoint(Integer.valueOf(tds.get(1).text()));
                matchPointBean.setMatchResult(tds.get(2).text());
                pointBean.add(matchPointBean);
            }
        }
        return result;
    }


    private static Map<String,JSONArray> resolveScript(Document document){
        Map<String,JSONArray> dataMap = new HashMap<>();
        Element script = document.getElementsByTag("script").get(1);
        // 循环解析data
        String value = script.data();
        while (value.contains("data: [")){
            value = value.substring(value.indexOf("data: [") + 6);
            String dataValue = value.substring(0,value.indexOf("]")+1);
            dataValue = dataValue.replaceAll("\t","").replace("\n","").replaceAll(" ","");
            try {
                JSONArray data = JSON.parseArray(dataValue);
                if (dataValue.contains("胜") && dataValue.contains("负")){
                    dataMap.put("胜率",data);
                }
                else if (dataValue.contains("labelLine") && dataValue.contains("label")){
                    dataMap.put("场地",data);
                }
            }catch (Exception e){

            }
        }
        return dataMap;
    }


    /**
     * 请求查询运动员信息
     * @return
     */
    private static String doPageAtpPlayer(String uid){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accept-encoding", "gzip");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-language", "zh-CN");
        headerMap.put("content-type", "application/x-www-form-urlencoded");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headerMap.put("x-csrf-token", "8ALl7sPryMjGO3sAKZXLfU9p1FB0DwbwowIyWiSE");
        headerMap.put("x-requested-with", "XMLHttpRequest");

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",uid);

        return HttpRequest
                .post(PLAYER_REPTILE_UTL)
                .headerMap(headerMap,true)
                .form(paramMap)
                .timeout(PLAYER_REPTILE_UTL_TIMEOUT)
                .execute().body();
    }



    /**
     * 请求查询运动员信息
     * @return
     */
    private static String doPageAtpPlayerForWta(String uid){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accept-encoding", "gzip");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-language", "zh-CN");
        headerMap.put("content-type", "application/x-www-form-urlencoded");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headerMap.put("x-csrf-token", "8ALl7sPryMjGO3sAKZXLfU9p1FB0DwbwowIyWiSE");
        headerMap.put("x-requested-with", "XMLHttpRequest");

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",uid);

        return HttpRequest
                .post(PLAYER_REPTILE_UTL_WTA)
                .headerMap(headerMap,true)
                .form(paramMap)
                .timeout(PLAYER_REPTILE_UTL_TIMEOUT)
                .execute().body();
    }
}
