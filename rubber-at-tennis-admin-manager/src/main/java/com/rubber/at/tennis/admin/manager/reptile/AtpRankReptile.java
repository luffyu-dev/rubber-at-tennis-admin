package com.rubber.at.tennis.admin.manager.reptile;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.rubber.at.tennis.admin.manager.model.ReptileModel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luffyu
 * Created on 2022/5/20
 */
@Slf4j
public class AtpRankReptile {


    /**
     * 排名URL
     */
    private static final String RANK_URL = "https://www.rank-tennis.com/zh/rank/atp/s/year/query";

    /**
     * 超时时间
     */
    private static final int PLAYER_REPTILE_UTL_TIMEOUT = 20000;


    public static void main(String[] args) {
        JSONObject jsonObject = queryAtpRank(0, 100);
        System.out.println(jsonObject);
    }


    /**
     * 分页查询apt的排名信息
     * @param model 当前的对象
     */
    public static void pageAtpRank(ReptileModel model){
        try {
            JSONObject data = queryAtpRank(model.getIndex(), model.getSize());
            model.setData(data.getJSONArray("data"));
            model.setRequestSuccess(true);
            model.setTotal(data.getInteger("recordsTotal"));
        }catch (Exception e){
            log.error("请求失败={}",e.getMessage());
            model.setRequestSuccess(false);
            model.setMsg(e.getMessage());
        }
    }





    public static JSONObject queryAtpRank(int page,int size) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("accept-encoding", "gzip");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-language", "zh-CN");
        headerMap.put("content-type", "application/x-www-form-urlencoded");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headerMap.put("x-csrf-token", "8ALl7sPryMjGO3sAKZXLfU9p1FB0DwbwowIyWiSE");
        headerMap.put("x-requested-with", "XMLHttpRequest");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", page);
        paramMap.put("length", size);
        paramMap.put("draw", "2");
        paramMap.put("columns[0][data]", "c_rank");
        paramMap.put("columns[0][name]", "c_rank");
        paramMap.put("columns[0][searchable]", "true");
        paramMap.put("columns[0][orderable]", "true");
        paramMap.put("columns[0][search][value]", "");
        paramMap.put("columns[0][search][regex]", "false");
        paramMap.put("columns[1][data]", "point");
        paramMap.put("columns[1][name]", "point");
        paramMap.put("columns[1][searchable]", "true");
        paramMap.put("columns[1][orderable]", "true");
        paramMap.put("columns[1][search][value]", "");
        paramMap.put("columns[1][search][regex]", "false");
        paramMap.put("columns[2][data]", "full_name");
        paramMap.put("columns[2][name]", "full_name");
        paramMap.put("columns[2][searchable]", "true");
        paramMap.put("columns[2][orderable]", "true");
        paramMap.put("columns[2][search][value]", "");
        paramMap.put("columns[2][search][regex]", "false");
        paramMap.put("columns[3][data]", "eng_name");
        paramMap.put("columns[3][name]", "eng_name");
        paramMap.put("columns[3][searchable]", "true");
        paramMap.put("columns[3][orderable]", "true");
        paramMap.put("columns[3][search][value]", "");
        paramMap.put("columns[3][search][regex]", "false");
        paramMap.put("columns[4][data]", "change");
        paramMap.put("columns[4][name]", "change");
        paramMap.put("columns[4][searchable]", "true");
        paramMap.put("columns[4][orderable]", "true");
        paramMap.put("columns[4][search][value]", "");
        paramMap.put("columns[4][search][regex]", "false");
        paramMap.put("columns[5][data]", "f_rank");
        paramMap.put("columns[5][name]", "f_rank");
        paramMap.put("columns[5][searchable]", "true");
        paramMap.put("columns[5][orderable]", "true");
        paramMap.put("columns[5][search][value]", "");
        paramMap.put("columns[5][search][regex]", "false");
        paramMap.put("columns[6][data]", "highest");
        paramMap.put("columns[6][name]", "highest");
        paramMap.put("columns[6][searchable]", "true");
        paramMap.put("columns[6][orderable]", "true");
        paramMap.put("columns[6][search][value]", "");
        paramMap.put("columns[6][search][regex]", "false");
        paramMap.put("columns[7][data]", "alt_point");
        paramMap.put("columns[7][name]", "alt_point");
        paramMap.put("columns[7][searchable]", "true");
        paramMap.put("columns[7][orderable]", "true");
        paramMap.put("columns[7][search][value]", "");
        paramMap.put("columns[7][search][regex]", "false");
        paramMap.put("columns[8][data]", "flop");
        paramMap.put("columns[8][name]", "flop");
        paramMap.put("columns[8][searchable]", "true");
        paramMap.put("columns[8][orderable]", "true");
        paramMap.put("columns[8][search][value]", "");
        paramMap.put("columns[8][search][regex]", "false");
        paramMap.put("columns[9][data]", "w_point");
        paramMap.put("columns[9][name]", "w_point");
        paramMap.put("columns[9][searchable]", "true");
        paramMap.put("columns[9][orderable]", "true");
        paramMap.put("columns[9][search][value]", "");
        paramMap.put("columns[9][search][regex]", "false");
        paramMap.put("columns[10][data]", "engname");
        paramMap.put("columns[10][name]", "engname");
        paramMap.put("columns[10][searchable]", "true");
        paramMap.put("columns[10][orderable]", "true");
        paramMap.put("columns[10][search][value]", "");
        paramMap.put("columns[10][search][regex]", "false");
        paramMap.put("columns[11][data]", "age");
        paramMap.put("columns[11][name]", "age");
        paramMap.put("columns[11][searchable]", "true");
        paramMap.put("columns[11][orderable]", "true");
        paramMap.put("columns[11][search][value]", "");
        paramMap.put("columns[11][search][regex]", "false");
        paramMap.put("columns[12][data]", "birth");
        paramMap.put("columns[12][name]", "birth");
        paramMap.put("columns[12][searchable]", "true");
        paramMap.put("columns[12][orderable]", "true");
        paramMap.put("columns[12][search][value]", "");
        paramMap.put("columns[12][search][regex]", "false");
        paramMap.put("columns[13][data]", "nation");
        paramMap.put("columns[13][name]", "nation");
        paramMap.put("columns[13][searchable]", "true");
        paramMap.put("columns[13][orderable]", "true");
        paramMap.put("columns[13][search][value]", "");
        paramMap.put("columns[13][search][regex]", "false");
        paramMap.put("columns[14][data]", "id");
        paramMap.put("columns[14][name]", "id");
        paramMap.put("columns[14][searchable]", "true");
        paramMap.put("columns[14][orderable]", "true");
        paramMap.put("columns[14][search][value]", "");
        paramMap.put("columns[14][search][regex]", "false");
        paramMap.put("columns[15][data]", "ioc");
        paramMap.put("columns[15][name]", "ioc");
        paramMap.put("columns[15][searchable]", "true");
        paramMap.put("columns[15][orderable]", "true");
        paramMap.put("columns[15][search][value]", "");
        paramMap.put("columns[15][search][regex]", "false");
        paramMap.put("columns[16][data]", "titles");
        paramMap.put("columns[16][name]", "titles");
        paramMap.put("columns[16][searchable]", "true");
        paramMap.put("columns[16][orderable]", "true");
        paramMap.put("columns[16][search][value]", "");
        paramMap.put("columns[16][search][regex]", "false");
        paramMap.put("columns[17][data]", "tour_c");
        paramMap.put("columns[17][name]", "tour_c");
        paramMap.put("columns[17][searchable]", "true");
        paramMap.put("columns[17][orderable]", "true");
        paramMap.put("columns[17][search][value]", "");
        paramMap.put("columns[17][search][regex]", "false");
        paramMap.put("columns[18][data]", "mand_0");
        paramMap.put("columns[18][name]", "mand_0");
        paramMap.put("columns[18][searchable]", "true");
        paramMap.put("columns[18][orderable]", "true");
        paramMap.put("columns[18][search][value]", "");
        paramMap.put("columns[18][search][regex]", "false");
        paramMap.put("columns[19][data]", "streak");
        paramMap.put("columns[19][name]", "streak");
        paramMap.put("columns[19][searchable]", "true");
        paramMap.put("columns[19][orderable]", "true");
        paramMap.put("columns[19][search][value]", "");
        paramMap.put("columns[19][search][regex]", "false");
        paramMap.put("columns[20][data]", "prize");
        paramMap.put("columns[20][name]", "prize");
        paramMap.put("columns[20][searchable]", "true");
        paramMap.put("columns[20][orderable]", "true");
        paramMap.put("columns[20][search][value]", "");
        paramMap.put("columns[20][search][regex]", "false");
        paramMap.put("columns[21][data]", "win");
        paramMap.put("columns[21][name]", "win");
        paramMap.put("columns[21][searchable]", "true");
        paramMap.put("columns[21][orderable]", "true");
        paramMap.put("columns[21][search][value]", "");
        paramMap.put("columns[21][search][regex]", "false");
        paramMap.put("columns[22][data]", "lose");
        paramMap.put("columns[22][name]", "lose");
        paramMap.put("columns[22][searchable]", "true");
        paramMap.put("columns[22][orderable]", "true");
        paramMap.put("columns[22][search][value]", "");
        paramMap.put("columns[22][search][regex]", "false");
        paramMap.put("columns[23][data]", "win_r");
        paramMap.put("columns[23][name]", "win_r");
        paramMap.put("columns[23][searchable]", "true");
        paramMap.put("columns[23][orderable]", "true");
        paramMap.put("columns[23][search][value]", "");
        paramMap.put("columns[23][search][regex]", "false");
        paramMap.put("columns[24][data]", "q_tour");
        paramMap.put("columns[24][name]", "q_tour");
        paramMap.put("columns[24][searchable]", "true");
        paramMap.put("columns[24][orderable]", "true");
        paramMap.put("columns[24][search][value]", "");
        paramMap.put("columns[24][search][regex]", "false");
        paramMap.put("columns[25][data]", "q_point");
        paramMap.put("columns[25][name]", "q_point");
        paramMap.put("columns[25][searchable]", "true");
        paramMap.put("columns[25][orderable]", "true");
        paramMap.put("columns[25][search][value]", "");
        paramMap.put("columns[25][search][regex]", "false");
        paramMap.put("columns[26][data]", "w_in");
        paramMap.put("columns[26][name]", "w_in");
        paramMap.put("columns[26][searchable]", "true");
        paramMap.put("columns[26][orderable]", "true");
        paramMap.put("columns[26][search][value]", "");
        paramMap.put("columns[26][search][regex]", "false");
        paramMap.put("columns[27][data]", "w_tour");
        paramMap.put("columns[27][name]", "w_tour");
        paramMap.put("columns[27][searchable]", "true");
        paramMap.put("columns[27][orderable]", "true");
        paramMap.put("columns[27][search][value]", "");
        paramMap.put("columns[27][search][regex]", "false");
        paramMap.put("columns[28][data]", "partner");
        paramMap.put("columns[28][name]", "partner");
        paramMap.put("columns[28][searchable]", "true");
        paramMap.put("columns[28][orderable]", "true");
        paramMap.put("columns[28][search][value]", "");
        paramMap.put("columns[28][search][regex]", "false");
        paramMap.put("columns[29][data]", "next_oppo");
        paramMap.put("columns[29][name]", "next_oppo");
        paramMap.put("columns[29][searchable]", "true");
        paramMap.put("columns[29][orderable]", "true");
        paramMap.put("columns[29][search][value]", "");
        paramMap.put("columns[29][search][regex]", "false");
        paramMap.put("columns[30][data]", "next_h2h");
        paramMap.put("columns[30][name]", "next_h2h");
        paramMap.put("columns[30][searchable]", "true");
        paramMap.put("columns[30][orderable]", "true");
        paramMap.put("columns[30][search][value]", "");
        paramMap.put("columns[30][search][regex]", "false");
        paramMap.put("columns[31][data]", "predict_R64");
        paramMap.put("columns[31][name]", "predict_R64");
        paramMap.put("columns[31][searchable]", "true");
        paramMap.put("columns[31][orderable]", "true");
        paramMap.put("columns[31][search][value]", "");
        paramMap.put("columns[31][search][regex]", "false");
        paramMap.put("columns[32][data]", "predict_R32");
        paramMap.put("columns[32][name]", "predict_R32");
        paramMap.put("columns[32][searchable]", "true");
        paramMap.put("columns[32][orderable]", "true");
        paramMap.put("columns[32][search][value]", "");
        paramMap.put("columns[32][search][regex]", "false");
        paramMap.put("columns[33][data]", "predict_R16");
        paramMap.put("columns[33][name]", "predict_R16");
        paramMap.put("columns[33][searchable]", "true");
        paramMap.put("columns[33][orderable]", "true");
        paramMap.put("columns[33][search][value]", "");
        paramMap.put("columns[33][search][regex]", "false");
        paramMap.put("columns[34][data]", "predict_QF");
        paramMap.put("columns[34][name]", "predict_QF");
        paramMap.put("columns[34][searchable]", "true");
        paramMap.put("columns[34][orderable]", "true");
        paramMap.put("columns[34][search][value]", "");
        paramMap.put("columns[34][search][regex]", "false");
        paramMap.put("columns[35][data]", "predict_SF");
        paramMap.put("columns[35][name]", "predict_SF");
        paramMap.put("columns[35][searchable]", "true");
        paramMap.put("columns[35][orderable]", "true");
        paramMap.put("columns[35][search][value]", "");
        paramMap.put("columns[35][search][regex]", "false");
        paramMap.put("columns[36][data]", "predict_F");
        paramMap.put("columns[36][name]", "predict_F");
        paramMap.put("columns[36][searchable]", "true");
        paramMap.put("columns[36][orderable]", "true");
        paramMap.put("columns[36][search][value]", "");
        paramMap.put("columns[36][search][regex]", "false");
        paramMap.put("columns[37][data]", "predict_W");
        paramMap.put("columns[37][name]", "predict_W");
        paramMap.put("columns[37][searchable]", "true");
        paramMap.put("columns[37][orderable]", "true");
        paramMap.put("columns[37][search][value]", "");
        paramMap.put("columns[37][search][regex]", "false");
        paramMap.put("order[0][column]", "0");
        paramMap.put("order[0][dir]", "asc");

        paramMap.put("search[value]", "");
        paramMap.put("search[regex]", "false");
        paramMap.put("device", "0");


        String data = HttpRequest
                .post(RANK_URL)
                .headerMap(headerMap, true)
                .form(paramMap)
                .timeout(PLAYER_REPTILE_UTL_TIMEOUT)
                .execute()
                .body();

        return JSONObject.parseObject(data);
    }
}
