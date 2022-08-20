package com.rubber.at.tennis.admin.manager.reptile;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rubber.at.tennis.admin.manager.model.ReptileModel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luffyu
 * Created on 2022/5/14
 */
@Slf4j
public class AtpPlayerReptile {

    /**
     * 数据域名
     */
    private static final String PLAYER_REPTILE_UTL = "https://www.rank-tennis.com/zh/profile/atp/query";

    /**
     * 超时时间
     */
    private static final int PLAYER_REPTILE_UTL_TIMEOUT = 20000;

    public static void main(String[] args) {
        JSONObject data = doPageAtpPlayer(0,10);
        System.out.println(data);
    }

    /**
     * 分页查询apt的运动员信息
     */
    public static void queryPageAtpPlayer(ReptileModel model){
        try {
            JSONObject data = doPageAtpPlayer(model.getIndex(), model.getSize());
            model.setData(data.getJSONArray("data"));
            model.setRequestSuccess(true);
            model.setTotal(data.getInteger("recordsTotal"));
        }catch (Exception e){
            log.error("请求失败={}",e.getMessage());
            model.setRequestSuccess(false);
            model.setMsg(e.getMessage());
        }
    }

    /**
     * 通过英文名称查询运动员信息
     */
    public static JSONArray queryPlayerByName(String name){
        try {
            JSONObject data = queryByEnglishName(name);
            return data.getJSONArray("data");
        }catch (Exception e){
            log.error("请求失败={}",e.getMessage());
            return null;
        }
    }


    /**
     * 请求查询运动员信息
     * @return
     */
    public static JSONObject doPageAtpPlayer(int page,int size){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accept-encoding", "gzip");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-language", "zh-CN");
        headerMap.put("content-type", "application/x-www-form-urlencoded");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headerMap.put("x-csrf-token", "8ALl7sPryMjGO3sAKZXLfU9p1FB0DwbwowIyWiSE");
        headerMap.put("x-requested-with", "XMLHttpRequest");

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("draw","1");
        paramMap.put("start",String.valueOf(page));
        paramMap.put("length",String.valueOf(size));

        paramMap.put("columns[0][data]","name");
        paramMap.put("columns[0][name]","name");
        paramMap.put("columns[0][searchable]","true");
        paramMap.put("columns[0][orderable]","true");
        paramMap.put("columns[0][search][value]","");
        paramMap.put("columns[0][search][regex]","false");
        paramMap.put("columns[1][data]","ioc");
        paramMap.put("columns[1][name]","ioc");
        paramMap.put("columns[1][searchable]","true");
        paramMap.put("columns[1][orderable]","true");
        paramMap.put("columns[1][search][value]","");
        paramMap.put("columns[1][search][regex]","false");
        paramMap.put("columns[2][data]","nationfull");
        paramMap.put("columns[2][name]","nationfull");
        paramMap.put("columns[2][searchable]","true");
        paramMap.put("columns[2][orderable]","true");
        paramMap.put("columns[2][search][value]","");
        paramMap.put("columns[2][search][regex]","false");
        paramMap.put("columns[3][data]","birthday");
        paramMap.put("columns[3][name]","birthday");
        paramMap.put("columns[3][searchable]","true");
        paramMap.put("columns[3][orderable]","true");
        paramMap.put("columns[3][search][value]","");
        paramMap.put("columns[3][search][regex]","false");
        paramMap.put("columns[4][data]","birthplace");
        paramMap.put("columns[4][name]","birthplace");
        paramMap.put("columns[4][searchable]","true");
        paramMap.put("columns[4][orderable]","true");
        paramMap.put("columns[4][search][value]","");
        paramMap.put("columns[4][search][regex]","false");
        paramMap.put("columns[5][data]","residence");
        paramMap.put("columns[5][name]","residence");
        paramMap.put("columns[5][searchable]","true");
        paramMap.put("columns[5][orderable]","true");
        paramMap.put("columns[5][search][value]","");
        paramMap.put("columns[5][search][regex]","false");
        paramMap.put("columns[6][data]","height_bri");
        paramMap.put("columns[6][name]","height_bri");
        paramMap.put("columns[6][searchable]","true");
        paramMap.put("columns[6][orderable]","true");
        paramMap.put("columns[6][search][value]","");
        paramMap.put("columns[6][search][regex]","false");
        paramMap.put("columns[7][data]","height");
        paramMap.put("columns[7][name]","height");
        paramMap.put("columns[7][searchable]","true");
        paramMap.put("columns[7][orderable]","true");
        paramMap.put("columns[7][search][value]","");
        paramMap.put("columns[7][search][regex]","false");
        paramMap.put("columns[8][data]","weight_bri");
        paramMap.put("columns[8][name]","weight_bri");
        paramMap.put("columns[8][searchable]","true");
        paramMap.put("columns[8][orderable]","true");
        paramMap.put("columns[8][search][value]","");
        paramMap.put("columns[8][search][regex]","false");
        paramMap.put("columns[9][data]","weight");
        paramMap.put("columns[9][name]","weight");
        paramMap.put("columns[9][searchable]","true");
        paramMap.put("columns[9][orderable]","true");
        paramMap.put("columns[9][search][value]","");
        paramMap.put("columns[9][search][regex]","false");
        paramMap.put("columns[10][data]","hand");
        paramMap.put("columns[10][name]","hand");
        paramMap.put("columns[10][searchable]","true");
        paramMap.put("columns[10][orderable]","true");
        paramMap.put("columns[10][search][value]","");
        paramMap.put("columns[10][search][regex]","false");
        paramMap.put("columns[11][data]","proyear");
        paramMap.put("columns[11][name]","proyear");
        paramMap.put("columns[11][searchable]","true");
        paramMap.put("columns[11][orderable]","true");
        paramMap.put("columns[11][search][value]","");
        paramMap.put("columns[11][search][regex]","false");
        paramMap.put("columns[12][data]","pronoun");
        paramMap.put("columns[12][name]","pronoun");
        paramMap.put("columns[12][searchable]","true");
        paramMap.put("columns[12][orderable]","true");
        paramMap.put("columns[12][search][value]","");
        paramMap.put("columns[12][search][regex]","false");
        paramMap.put("columns[13][data]","website");
        paramMap.put("columns[13][name]","website");
        paramMap.put("columns[13][searchable]","true");
        paramMap.put("columns[13][orderable]","true");
        paramMap.put("columns[13][search][value]","");
        paramMap.put("columns[13][search][regex]","false");
        paramMap.put("columns[14][data]","prize_c");
        paramMap.put("columns[14][name]","prize_c");
        paramMap.put("columns[14][searchable]","true");
        paramMap.put("columns[14][orderable]","true");
        paramMap.put("columns[14][search][value]","");
        paramMap.put("columns[14][search][regex]","false");
        paramMap.put("columns[15][data]","prize_y");
        paramMap.put("columns[15][name]","prize_y");
        paramMap.put("columns[15][searchable]","true");
        paramMap.put("columns[15][orderable]","true");
        paramMap.put("columns[15][search][value]","");
        paramMap.put("columns[15][search][regex]","false");
        paramMap.put("columns[16][data]","rank_s");
        paramMap.put("columns[16][name]","rank_s");
        paramMap.put("columns[16][searchable]","true");
        paramMap.put("columns[16][orderable]","true");
        paramMap.put("columns[16][search][value]","");
        paramMap.put("columns[16][search][regex]","false");
        paramMap.put("columns[17][data]","rank_s_hi");
        paramMap.put("columns[17][name]","rank_s_hi");
        paramMap.put("columns[17][searchable]","true");
        paramMap.put("columns[17][orderable]","true");
        paramMap.put("columns[17][search][value]","");
        paramMap.put("columns[17][search][regex]","false");
        paramMap.put("columns[18][data]","rank_s_hi_date");
        paramMap.put("columns[18][name]","rank_s_hi_date");
        paramMap.put("columns[18][searchable]","true");
        paramMap.put("columns[18][orderable]","true");
        paramMap.put("columns[18][search][value]","");
        paramMap.put("columns[18][search][regex]","false");
        paramMap.put("columns[19][data]","title_s_c");
        paramMap.put("columns[19][name]","title_s_c");
        paramMap.put("columns[19][searchable]","true");
        paramMap.put("columns[19][orderable]","true");
        paramMap.put("columns[19][search][value]","");
        paramMap.put("columns[19][search][regex]","false");
        paramMap.put("columns[20][data]","title_s_y");
        paramMap.put("columns[20][name]","title_s_y");
        paramMap.put("columns[20][searchable]","true");
        paramMap.put("columns[20][orderable]","true");
        paramMap.put("columns[20][search][value]","");
        paramMap.put("columns[20][search][regex]","false");
        paramMap.put("columns[21][data]","win_s_c");
        paramMap.put("columns[21][name]","win_s_c");
        paramMap.put("columns[21][searchable]","true");
        paramMap.put("columns[21][orderable]","true");
        paramMap.put("columns[21][search][value]","");
        paramMap.put("columns[21][search][regex]","false");
        paramMap.put("columns[22][data]","lose_s_c");
        paramMap.put("columns[22][name]","lose_s_c");
        paramMap.put("columns[22][searchable]","true");
        paramMap.put("columns[22][orderable]","true");
        paramMap.put("columns[22][search][value]","");
        paramMap.put("columns[22][search][regex]","false");
        paramMap.put("columns[23][data]","win_s_y");
        paramMap.put("columns[23][name]","win_s_y");
        paramMap.put("columns[23][searchable]","true");
        paramMap.put("columns[23][orderable]","true");
        paramMap.put("columns[23][search][value]","");
        paramMap.put("columns[23][search][regex]","false");
        paramMap.put("columns[24][data]","lose_s_y");
        paramMap.put("columns[24][name]","lose_s_y");
        paramMap.put("columns[24][searchable]","true");
        paramMap.put("columns[24][orderable]","true");
        paramMap.put("columns[24][search][value]","");
        paramMap.put("columns[24][search][regex]","false");
        paramMap.put("columns[25][data]","rank_d");
        paramMap.put("columns[25][name]","rank_d");
        paramMap.put("columns[25][searchable]","true");
        paramMap.put("columns[25][orderable]","true");
        paramMap.put("columns[25][search][value]","");
        paramMap.put("columns[25][search][regex]","false");
        paramMap.put("columns[26][data]","rank_d_hi");
        paramMap.put("columns[26][name]","rank_d_hi");
        paramMap.put("columns[26][searchable]","true");
        paramMap.put("columns[26][orderable]","true");
        paramMap.put("columns[26][search][value]","");
        paramMap.put("columns[26][search][regex]","false");
        paramMap.put("columns[27][data]","rank_d_hi_date");
        paramMap.put("columns[27][name]","rank_d_hi_date");
        paramMap.put("columns[27][searchable]","true");
        paramMap.put("columns[27][orderable]","true");
        paramMap.put("columns[27][search][value]","");
        paramMap.put("columns[27][search][regex]","false");
        paramMap.put("columns[28][data]","title_d_c");
        paramMap.put("columns[28][name]","title_d_c");
        paramMap.put("columns[28][searchable]","true");
        paramMap.put("columns[28][orderable]","true");
        paramMap.put("columns[28][search][value]","");
        paramMap.put("columns[28][search][regex]","false");
        paramMap.put("columns[29][data]","title_d_y");
        paramMap.put("columns[29][name]","title_d_y");
        paramMap.put("columns[29][searchable]","true");
        paramMap.put("columns[29][orderable]","true");
        paramMap.put("columns[29][search][value]","");
        paramMap.put("columns[29][search][regex]","false");
        paramMap.put("columns[30][data]","win_d_c");
        paramMap.put("columns[30][name]","win_d_c");
        paramMap.put("columns[30][searchable]","true");
        paramMap.put("columns[30][orderable]","true");
        paramMap.put("columns[30][search][value]","");
        paramMap.put("columns[30][search][regex]","false");
        paramMap.put("columns[31][data]","lose_d_c");
        paramMap.put("columns[31][name]","lose_d_c");
        paramMap.put("columns[31][searchable]","true");
        paramMap.put("columns[31][orderable]","true");
        paramMap.put("columns[31][search][value]","");
        paramMap.put("columns[31][search][regex]","false");
        paramMap.put("columns[32][data]","win_d_y");
        paramMap.put("columns[32][name]","win_d_y");
        paramMap.put("columns[32][searchable]","true");
        paramMap.put("columns[32][orderable]","true");
        paramMap.put("columns[32][search][value]","");
        paramMap.put("columns[32][search][regex]","false");
        paramMap.put("columns[33][data]","lose_d_y");
        paramMap.put("columns[33][name]","lose_d_y");
        paramMap.put("columns[33][searchable]","true");
        paramMap.put("columns[33][orderable]","true");
        paramMap.put("columns[33][search][value]","");
        paramMap.put("columns[33][search][regex]","false");
        paramMap.put("order[0][column]","14");
        paramMap.put("order[0][dir]","desc");
        paramMap.put("search[value]","");
        paramMap.put("search[regex]","false");
        paramMap.put("device","0");
        String data = HttpRequest
                .post(PLAYER_REPTILE_UTL)
                .headerMap(headerMap,true)
                .form(paramMap)
                .timeout(PLAYER_REPTILE_UTL_TIMEOUT)
                .execute().body();
        return JSONObject.parseObject(data);
    }


    /**
     * 通过英文名称搜索
     * @param englishName
     * @return
     */
    public static JSONObject queryByEnglishName(String englishName){
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accept-encoding", "gzip");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-language", "zh-CN");
        headerMap.put("content-type", "application/x-www-form-urlencoded");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headerMap.put("x-csrf-token", "8ALl7sPryMjGO3sAKZXLfU9p1FB0DwbwowIyWiSE");
        headerMap.put("x-requested-with", "XMLHttpRequest");

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("draw","8");
        paramMap.put("columns[0][data]","name");
        paramMap.put("columns[0][name]","name");
        paramMap.put("columns[0][searchable]","true");
        paramMap.put("columns[0][orderable]","true");
        paramMap.put("columns[0][search][value]",englishName);
        paramMap.put("columns[0][search][regex]","false");
        paramMap.put("columns[1][data]","ioc");
        paramMap.put("columns[1][name]","ioc");
        paramMap.put("columns[1][searchable]","true");
        paramMap.put("columns[1][orderable]","true");
        paramMap.put("columns[1][search][value]","");
        paramMap.put("columns[1][search][regex]","false");
        paramMap.put("columns[2][data]","nationfull");
        paramMap.put("columns[2][name]","nationfull");
        paramMap.put("columns[2][searchable]","true");
        paramMap.put("columns[2][orderable]","true");
        paramMap.put("columns[2][search][value]","");
        paramMap.put("columns[2][search][regex]","false");
        paramMap.put("columns[3][data]","birthday");
        paramMap.put("columns[3][name]","birthday");
        paramMap.put("columns[3][searchable]","true");
        paramMap.put("columns[3][orderable]","true");
        paramMap.put("columns[3][search][value]","");
        paramMap.put("columns[3][search][regex]","false");
        paramMap.put("columns[4][data]","birthplace");
        paramMap.put("columns[4][name]","birthplace");
        paramMap.put("columns[4][searchable]","true");
        paramMap.put("columns[4][orderable]","true");
        paramMap.put("columns[4][search][value]","");
        paramMap.put("columns[4][search][regex]","false");
        paramMap.put("columns[5][data]","residence");
        paramMap.put("columns[5][name]","residence");
        paramMap.put("columns[5][searchable]","true");
        paramMap.put("columns[5][orderable]","true");
        paramMap.put("columns[5][search][value]","");
        paramMap.put("columns[5][search][regex]","false");
        paramMap.put("columns[6][data]","height_bri");
        paramMap.put("columns[6][name]","height_bri");
        paramMap.put("columns[6][searchable]","true");
        paramMap.put("columns[6][orderable]","true");
        paramMap.put("columns[6][search][value]","");
        paramMap.put("columns[6][search][regex]","false");
        paramMap.put("columns[7][data]","height");
        paramMap.put("columns[7][name]","height");
        paramMap.put("columns[7][searchable]","true");
        paramMap.put("columns[7][orderable]","true");
        paramMap.put("columns[7][search][value]","");
        paramMap.put("columns[7][search][regex]","false");
        paramMap.put("columns[8][data]","weight_bri");
        paramMap.put("columns[8][name]","weight_bri");
        paramMap.put("columns[8][searchable]","true");
        paramMap.put("columns[8][orderable]","true");
        paramMap.put("columns[8][search][value]","");
        paramMap.put("columns[8][search][regex]","false");
        paramMap.put("columns[9][data]","weight");
        paramMap.put("columns[9][name]","weight");
        paramMap.put("columns[9][searchable]","true");
        paramMap.put("columns[9][orderable]","true");
        paramMap.put("columns[9][search][value]","");
        paramMap.put("columns[9][search][regex]","false");
        paramMap.put("columns[10][data]","hand");
        paramMap.put("columns[10][name]","hand");
        paramMap.put("columns[10][searchable]","true");
        paramMap.put("columns[10][orderable]","true");
        paramMap.put("columns[10][search][value]","");
        paramMap.put("columns[10][search][regex]","false");
        paramMap.put("columns[11][data]","proyear");
        paramMap.put("columns[11][name]","proyear");
        paramMap.put("columns[11][searchable]","true");
        paramMap.put("columns[11][orderable]","true");
        paramMap.put("columns[11][search][value]","");
        paramMap.put("columns[11][search][regex]","false");
        paramMap.put("columns[12][data]","pronoun");
        paramMap.put("columns[12][name]","pronoun");
        paramMap.put("columns[12][searchable]","true");
        paramMap.put("columns[12][orderable]","true");
        paramMap.put("columns[12][search][value]","");
        paramMap.put("columns[12][search][regex]","false");
        paramMap.put("columns[13][data]","website");
        paramMap.put("columns[13][name]","website");
        paramMap.put("columns[13][searchable]","true");
        paramMap.put("columns[13][orderable]","true");
        paramMap.put("columns[13][search][value]","");
        paramMap.put("columns[13][search][regex]","false");
        paramMap.put("columns[14][data]","prize_c");
        paramMap.put("columns[14][name]","prize_c");
        paramMap.put("columns[14][searchable]","true");
        paramMap.put("columns[14][orderable]","true");
        paramMap.put("columns[14][search][value]","");
        paramMap.put("columns[14][search][regex]","false");
        paramMap.put("columns[15][data]","prize_y");
        paramMap.put("columns[15][name]","prize_y");
        paramMap.put("columns[15][searchable]","true");
        paramMap.put("columns[15][orderable]","true");
        paramMap.put("columns[15][search][value]","");
        paramMap.put("columns[15][search][regex]","false");
        paramMap.put("columns[16][data]","rank_s");
        paramMap.put("columns[16][name]","rank_s");
        paramMap.put("columns[16][searchable]","true");
        paramMap.put("columns[16][orderable]","true");
        paramMap.put("columns[16][search][value]","");
        paramMap.put("columns[16][search][regex]","false");
        paramMap.put("columns[17][data]","rank_s_hi");
        paramMap.put("columns[17][name]","rank_s_hi");
        paramMap.put("columns[17][searchable]","true");
        paramMap.put("columns[17][orderable]","true");
        paramMap.put("columns[17][search][value]","");
        paramMap.put("columns[17][search][regex]","false");
        paramMap.put("columns[18][data]","rank_s_hi_date");
        paramMap.put("columns[18][name]","rank_s_hi_date");
        paramMap.put("columns[18][searchable]","true");
        paramMap.put("columns[18][orderable]","true");
        paramMap.put("columns[18][search][value]","");
        paramMap.put("columns[18][search][regex]","false");
        paramMap.put("columns[19][data]","title_s_c");
        paramMap.put("columns[19][name]","title_s_c");
        paramMap.put("columns[19][searchable]","true");
        paramMap.put("columns[19][orderable]","true");
        paramMap.put("columns[19][search][value]","");
        paramMap.put("columns[19][search][regex]","false");
        paramMap.put("columns[20][data]","title_s_y");
        paramMap.put("columns[20][name]","title_s_y");
        paramMap.put("columns[20][searchable]","true");
        paramMap.put("columns[20][orderable]","true");
        paramMap.put("columns[20][search][value]","");
        paramMap.put("columns[20][search][regex]","false");
        paramMap.put("columns[21][data]","win_s_c");
        paramMap.put("columns[21][name]","win_s_c");
        paramMap.put("columns[21][searchable]","true");
        paramMap.put("columns[21][orderable]","true");
        paramMap.put("columns[21][search][value]","");
        paramMap.put("columns[21][search][regex]","false");
        paramMap.put("columns[22][data]","lose_s_c");
        paramMap.put("columns[22][name]","lose_s_c");
        paramMap.put("columns[22][searchable]","true");
        paramMap.put("columns[22][orderable]","true");
        paramMap.put("columns[22][search][value]","");
        paramMap.put("columns[22][search][regex]","false");
        paramMap.put("columns[23][data]","win_s_y");
        paramMap.put("columns[23][name]","win_s_y");
        paramMap.put("columns[23][searchable]","true");
        paramMap.put("columns[23][orderable]","true");
        paramMap.put("columns[23][search][value]","");
        paramMap.put("columns[23][search][regex]","false");
        paramMap.put("columns[24][data]","lose_s_y");
        paramMap.put("columns[24][name]","lose_s_y");
        paramMap.put("columns[24][searchable]","true");
        paramMap.put("columns[24][orderable]","true");
        paramMap.put("columns[24][search][value]","");
        paramMap.put("columns[24][search][regex]","false");
        paramMap.put("columns[25][data]","rank_d");
        paramMap.put("columns[25][name]","rank_d");
        paramMap.put("columns[25][searchable]","true");
        paramMap.put("columns[25][orderable]","true");
        paramMap.put("columns[25][search][value]","");
        paramMap.put("columns[25][search][regex]","false");
        paramMap.put("columns[26][data]","rank_d_hi");
        paramMap.put("columns[26][name]","rank_d_hi");
        paramMap.put("columns[26][searchable]","true");
        paramMap.put("columns[26][orderable]","true");
        paramMap.put("columns[26][search][value]","");
        paramMap.put("columns[26][search][regex]","false");
        paramMap.put("columns[27][data]","rank_d_hi_date");
        paramMap.put("columns[27][name]","rank_d_hi_date");
        paramMap.put("columns[27][searchable]","true");
        paramMap.put("columns[27][orderable]","true");
        paramMap.put("columns[27][search][value]","");
        paramMap.put("columns[27][search][regex]","false");
        paramMap.put("columns[28][data]","title_d_c");
        paramMap.put("columns[28][name]","title_d_c");
        paramMap.put("columns[28][searchable]","true");
        paramMap.put("columns[28][orderable]","true");
        paramMap.put("columns[28][search][value]","");
        paramMap.put("columns[28][search][regex]","false");
        paramMap.put("columns[29][data]","title_d_y");
        paramMap.put("columns[29][name]","title_d_y");
        paramMap.put("columns[29][searchable]","true");
        paramMap.put("columns[29][orderable]","true");
        paramMap.put("columns[29][search][value]","");
        paramMap.put("columns[29][search][regex]","false");
        paramMap.put("columns[30][data]","win_d_c");
        paramMap.put("columns[30][name]","win_d_c");
        paramMap.put("columns[30][searchable]","true");
        paramMap.put("columns[30][orderable]","true");
        paramMap.put("columns[30][search][value]","");
        paramMap.put("columns[30][search][regex]","false");
        paramMap.put("columns[31][data]","lose_d_c");
        paramMap.put("columns[31][name]","lose_d_c");
        paramMap.put("columns[31][searchable]","true");
        paramMap.put("columns[31][orderable]","true");
        paramMap.put("columns[31][search][value]","");
        paramMap.put("columns[31][search][regex]","false");
        paramMap.put("columns[32][data]","win_d_y");
        paramMap.put("columns[32][name]","win_d_y");
        paramMap.put("columns[32][searchable]","true");
        paramMap.put("columns[32][orderable]","true");
        paramMap.put("columns[32][search][value]","");
        paramMap.put("columns[32][search][regex]","false");
        paramMap.put("columns[33][data]","lose_d_y");
        paramMap.put("columns[33][name]","lose_d_y");
        paramMap.put("columns[33][searchable]","true");
        paramMap.put("columns[33][orderable]","true");
        paramMap.put("columns[33][search][value]","");
        paramMap.put("columns[33][search][regex]","false");
        paramMap.put("order[0][column]","14");
        paramMap.put("order[0][dir]","desc");
        paramMap.put("start","0");
        paramMap.put("length","100");
        paramMap.put("search[value]","");
        paramMap.put("search[regex]","false");
        paramMap.put("device","0");
        String data = HttpRequest
                .post(PLAYER_REPTILE_UTL)
                .headerMap(headerMap,true)
                .form(paramMap)
                .timeout(PLAYER_REPTILE_UTL_TIMEOUT)
                .execute().body();
        return JSONObject.parseObject(data);
    }

}
