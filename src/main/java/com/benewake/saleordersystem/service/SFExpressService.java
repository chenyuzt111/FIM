package com.benewake.saleordersystem.service;


import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.sfexpress.Route;
import com.benewake.saleordersystem.entity.sfexpress.SF_SEARCH_RESULT;


/**
 * @author Lcs
 * 描述：顺丰查询信息接口
 */
public interface SFExpressService {

    /**
     * 获取完整路由查询结果
     *
     * @param code
     * @param tel
     * @return
     */
    SF_SEARCH_RESULT findRoutesByCode(String code, String tel);

    SF_SEARCH_RESULT searchPromitm(String code, String tel);

    /**
     * 根据订单单号获取 当前 最新的顺丰状态信息
     * @param delivery
     * @return
     * @throws Exception
     */
    Route getLastestRouteByFCarriageNO(Delivery delivery);
}
