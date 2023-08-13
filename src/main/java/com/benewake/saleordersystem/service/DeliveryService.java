package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.Inquiry;

import java.util.List;

/**
 * @author Lcs
 */
public interface DeliveryService {

    /**
     * 获取运输单号并更新订单运输路由状态
     * @return
     */
    boolean updateDelivery();

    int insertLists(List<Inquiry> lists);

    /**
     * 模糊匹配顺丰运输单号
     * @param deliveryCode
     * @return
     */
    List<Delivery> getDeliveryCodeList(String deliveryCode);

    /**
     * 模糊匹配最新状态情况
     * @param deliveryState
     * @return
     */
    List<String> getDeliveryStateList(String deliveryState);
}
