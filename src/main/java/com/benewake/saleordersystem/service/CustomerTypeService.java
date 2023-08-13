package com.benewake.saleordersystem.service;

/**
 * @author Lcs
 */
public interface CustomerTypeService {

    /**
     * 根据客户和物料获取客户类型
     * @param customerId
     * @param itemId
     * @return
     */
    String getCustomerTypeByRule(Long customerId,Long itemId);

}
