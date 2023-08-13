package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.Past.SaleOut;
import com.benewake.saleordersystem.entity.Past.Withdraw;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
public interface KingDeeService {

    /**
     * 从金蝶星空云中获取数据
     * @param formId 表单ID
     * @param fieldKeys 查询的字段
     * @param queryFilters 过滤条件
     * @param limit 每页查询数量
     * @param type 返回的数据对象
     * @return
     * @param <T>
     * @throws Exception
     */
    <T> List<T> searchData(String formId, List<String> fieldKeys, String queryFilters,int limit,Class type) throws Exception;

    /**
     * 获取id到name的映射表   1-物料映射 2-销售员映射 3-用户映射 4-客户映射 5-组织映射
     * @param choose
     * @return
     */
    Map<String,String> getIdToNameList(int choose) throws Exception;

    /**
     * 拉取销售出库单列表
     * @param queryFilters
     * @param limit
     * @return
     */
    List<SaleOut> searchSaleOutList(String queryFilters, int limit) throws Exception;

    /**
     * 拉取表1数据
     * @param limit
     * @return
     * @throws Exception
     */
    List<SaleOut> searchSaleOutList1(int limit, String time) throws Exception;

    /**
     * 拉取表2数据
     * @param limit
     * @return
     * @throws Exception
     */
    List<SaleOut> searchSaleOutList2(int limit, String time) throws Exception;

    /**
     * 拉取销售退货单列表
     * @param queryFilters
     * @param limit
     * @return
     */
    List<Withdraw> searchWithdrawList(String queryFilters, int limit) throws Exception;

    /**
     * 拉取表4数据
     * @param limit
     * @return
     * @throws Exception
     */
    List<Withdraw> searcWithdrawList1(int limit, String time) throws Exception;

    /**
     * 拉取表5数据
     * @param limit
     * @return
     * @throws Exception
     */
    List<Withdraw> searcWithdrawList2(int limit, String time) throws Exception;

    /**
     * 根据订单单号从销售出库单中取运输单号和收件人电话
     */
    List<SaleOut> selectFCarriageNO(List<Delivery> deliveries) throws Exception;
}

