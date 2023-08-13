package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.Customer;

import java.util.List;

/**
 * @author Lcs
 */
public interface CustomerService {

    /**
     * 从金蝶取数并更新用户id->用户名称映射表
     * @return
     */
    @Deprecated
    int updateCustomerDB();

    /**
     * 根据输入模糊搜索客户名称
     * @param customerName
     * @return
     */
    List<Customer> getCustomerLikeList(String customerName);

    /**
     * 判断客户id获取对象
     * @param customerId
     * @return
     */
    Customer findCustomerById(Long customerId);

    /**
     * 根据客户姓名获取对象
     * @param customerName
     * @return
     */
    Customer findCustomerByName(String customerName);

    /**
     * 获取客户类型模糊匹配列表
     * @param type
     * @return
     */
    List<String> getCustomerTypeLikeList(String type);
}
