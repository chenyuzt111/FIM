package com.benewake.saleordersystem.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lcs
 * 描 述：一些通用常量定义
 */
public interface BenewakeConstants {
    /**
     * 金蝶授权信息
     */
    String X_KDAPI_ACCTID = "20210222142620903";
    String X_KDAPI_USERNAME = "TF系列交付管理信息系统";
    String X_KDAPI_APPID = "252117_346u3ckO6Pl/Qe/L667BQaWr1JXb2KKH";
    String X_KDAPI_APPSEC = "d7cc71aa2cc74d4eb06f21fa322f2082";
    String X_KDAPI_SERVICEURL = "https://benewake.test.ik3cloud.com/k3cloud/";
    int X_KDAPI_LCID = 2052;

    /**
     * 一天的时间
     */
    int ONE_DAY = 1000*60*60*24;
    /**
     * 响应code：成功
     */
    Integer SUCCESS_CODE = 200;
    /**
     * 响应code：失败
     */
    Integer FAIL_CODE = 400;
    /**
     * 响应message：成功
     */
    String SUCCESS_MESSAGE = "success";
    /**
     * 响应message：失败
     */
    String FAIL_MESSAGE = "fail";
    /**
     * 默认状态的登录凭证超时时间 (7天)
     */
    int DEFAULT_EXPIRED_SECONDS = 3600*24*7;
    /**
     * 用户类型：管理员
     */
    Long USER_TYPE_ADMIN = 1L;
    /**
     * 用户类型：销售员
     */
    Long USER_TYPE_SALESMAN = 2L;
    /**
     * 用户类型：访客
     */
    Long USER_TYPE_VISITOR = 3L;
    /**
     * 用户类型：无效
     */
    Long USER_TYPE_INVALID = 4L;
    /**
     * 用户类型：系统
     */
    Long USER_TYPE_SYSTEM = 5L;

    /**
     * 筛选条件
     */
    String LIKE = "like";
    String NOT_LIKE = "notlike";
    String GREATER = "gt";
    String GREATER_OR_EQUAL = "ge";
    String EQUAL = "eq";
    String NOT_EQUAL = "ne";
    String LITTER = "lt";
    String LITTER_OR_EQUAL = "le";
    String IS_NULL = "null";
    String NOT_NULL = "notnull";

    /**
     * 订单类型：销售预测
     */
    int ORDER_TYPE_YC = 4;
    /**
     * 订单类型：询单
     */
    int ORDER_TYPE_XD = 5;
    /**
     * 订单类型：用户付款
     */
    int ORDER_TYPE_PO = 1;
    /**
     * 订单类型：用户付款意向
     */
    int ORDER_TYPE_PR = 2;
    /**
     * 订单类型：供应链预估
     */
    int ORDER_TYPE_YG = 3;

    /**
     * 产品类型：已有竞品
     */
    int ITEM_TYPE_ALREADY_AVAILABLE = 1;
    /**
     * 产品类型：已有定制
     */
    int ITEM_TYPE_ALREADY_BESPOKE = 2;
    /**
     * 产品类型：新增软件定制
     */
    int ITEM_TYPE_SOFTWARE_BESPOKE = 3;
    /**
     * 产品类型：新增原材料定制
     */
    int ITEM_TYPE_RAW_MATERIALS_BESPOKE = 4;
    /**
     * 产品类型：新增原材料+软件定制
     */
    int ITEM_TYPE_MATERIALS_AND_SOFTWARE_BESPOKE = 5;


    /**
     * 初始订单类型：销售询单
     */
    int INQUIRY_INIT_TYPE_XD = 5;
    /**
     * 初始订单类型：销售预测
     */
    int INQUIRY_INIT_TYPE_YC = 4;
    /**
     * 初始订单类型：供应链预估
     */
    int INQUIRY_INT_TYPE_YG = 3;


    /**
     * 表名：全部订单列表
     */
    Long ALL_TABLE = 1L;
    /**
     * 表名：订单状态
     */
    Long INQUIRY_TYPE_TABLE = 2L;
    /**
     * 表名：客户类型
     */
    Long CUSTOMER_TYPE_TABLE = 3L;
    /**
     * 表名：产品类型
     */
    Long ITEM_TYPE_TABLE = 4L;
    /**
     * 表名：订单转换
     */
    Long INQUIRY_CHANGE_TABLE = 5L;
    /**
     * 表名：订单交付进度
     */
    Long INQUIRY_DELIVERY_TABLE = 6L;



}
