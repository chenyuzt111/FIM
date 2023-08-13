package com.benewake.saleordersystem.utils.api;

import com.alibaba.fastjson2.JSONObject;
import com.benewake.saleordersystem.entity.sfexpress.Route;
import com.benewake.saleordersystem.entity.sfexpress.SF_SEARCH_RESULT;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lcs
 * @since 2023年07月07 10:05
 * 描 述： TODO
 */
public class SFUtils {

    /**
     * 解析路由查询结果 获得运送路径列表
     * @param result
     * @return
     */
    public static List<Route> parseToRoute(SF_SEARCH_RESULT result){
        List<Route> routes = new ArrayList<>();
        if(result == null) return routes;
        String msgData = JSONObject.parseObject(result.getApiResultData()).getString("msgData");
        if(StringUtils.isBlank(msgData)) return routes;
        String routeResps = JSONObject.parseObject(msgData).getJSONArray("routeResps").get(0).toString();
        if(StringUtils.isBlank(routeResps)) return routes;
        routes = JSONObject.parseObject(routeResps).getList("routes", Route.class);
        return routes;
    }

    /**
     * 解析并只获取最新消息
     * @param result
     * @return
     */
    public static Route getLastestRemark(SF_SEARCH_RESULT result){
        List<Route> routes = parseToRoute(result);
        if(routes==null || routes.size()==0) return new Route();
        if("8000".equals(routes.get(routes.size()-1).getOpCode())){
            return routes.get(routes.size() - 2);
        }else{
            return routes.get(routes.size() - 1);
        }
    }


}
