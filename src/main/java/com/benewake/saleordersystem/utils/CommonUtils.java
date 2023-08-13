package com.benewake.saleordersystem.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benewake.saleordersystem.entity.VO.FilterCriteria;
import com.benewake.saleordersystem.entity.sfexpress.Route;
import com.benewake.saleordersystem.entity.sfexpress.SF_SEARCH_RESULT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Lcs
 * 描述：一些通用工具
 */
public class CommonUtils implements BenewakeConstants{
    /**
     * 订单状态映射表
     */
    private static final Map<String,String> ORDER_STATUS_MAP;
    private static final Map<String,String> IS_CLOSE_MAP;
    static {
        ORDER_STATUS_MAP = new HashMap<>();
        ORDER_STATUS_MAP.put("A","创建");
        ORDER_STATUS_MAP.put("B","审核中");
        ORDER_STATUS_MAP.put("Z","暂存");
        ORDER_STATUS_MAP.put("C","已审核");
        ORDER_STATUS_MAP.put("D","重新审核");

        IS_CLOSE_MAP = new HashMap<>();
        IS_CLOSE_MAP.put("A","已关闭");
        IS_CLOSE_MAP.put("B","未关闭");
    }

    /**
     * 根据标识获取订单状态信息
     * @param key
     * @return
     */
    public static String getOrderStatus(String key){
        return ORDER_STATUS_MAP.get(key);
    }
    public static String getIsClose(String key){
        return IS_CLOSE_MAP.get(key);
    }

    // 生成随机字符串UUID
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    // MD5 加密
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code, String msg, Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("message",msg);
        if(map!=null){
            for(String key : map.keySet()){
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code,String msg){
        return getJSONString(code,msg,null);
    }

    public static String getJSONString(int code){
        return getJSONString(code,null,null);
    }

    /**
     * 将数据写入Excel
     * @param file
     * @param data
     * @param type
     * @return
     */
    public static boolean writeExcel(File file, List data,Class type,String sheetName){
        //2、判断文件是否存在，不存在则创建一个Excel文件
        if (!file.exists()) {
            try {
                file.createNewFile();//创建一个新的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3、指定需要那个class去写。然后写到第一个sheet，名字为模版，然后文件流会自动关闭
        EasyExcel.write(file, type).sheet(sheetName).doWrite(data);
        return true;
    }
    public static boolean writeExcel(File file,List data,Class type){
        return writeExcel(file, data, type, "sheet");
    }

    /**
     * 解析顺丰路由列表
     * @param result
     * @return
     */
    public static List<Route> paresRoutes(SF_SEARCH_RESULT result){
        String msgData = com.alibaba.fastjson2.JSONObject.parseObject(result.getApiResultData()).getString("msgData");
        String routeResps = com.alibaba.fastjson2.JSONObject.parseObject(msgData).getJSONArray("routeResps").get(0).toString();
        List<Route> routes = com.alibaba.fastjson2.JSONObject.parseObject(routeResps).getList("routes", Route.class);
        return routes;
    }

    /**
     * 添加筛选条件
     * @param filters
     * @param queryWrapper
     * @return
     */
    public static <T> boolean addFilters(List<FilterCriteria> filters, QueryWrapper<T> queryWrapper){
        filters.forEach(f ->{
            switch (f.getCondition()){
                case LIKE:
                    queryWrapper.like(f.getColName(),f.getValue());
                    break;
                case NOT_LIKE:
                    queryWrapper.notLike(f.getColName(),f.getValue());
                    break;
                case GREATER:
                    queryWrapper.gt(f.getColName(),f.getValue());
                    break;
                case GREATER_OR_EQUAL:
                    queryWrapper.ge(f.getColName(),f.getValue());
                    break;
                case EQUAL:
                    queryWrapper.eq(f.getColName(),f.getValue());
                    break;
                case NOT_EQUAL:
                    queryWrapper.ne(f.getColName(),f.getValue());
                    break;
                case LITTER:
                    queryWrapper.lt(f.getColName(),f.getValue());
                    break;
                case LITTER_OR_EQUAL:
                    queryWrapper.le(f.getColName(),f.getValue());
                    break;
                case IS_NULL:
                    queryWrapper.isNull(f.getColName());
                    break;
                case NOT_NULL:
                    queryWrapper.isNotNull(f.getColName());
                    break;
                default:
                    throw new IllegalArgumentException("筛选参数错误！");
            }
        });
        return true;
    }

    /**
     * 金蝶时间转换
     * @param date
     * @return
     */
    public static String KingDeeDateFormat(String date) throws ParseException {
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ft2.format(ft1.parse(date));
    }

}
