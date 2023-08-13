package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * 描 述：调用存储过程 返回值统一为Map<String,Object> 只需读不需要修改
 */
@Mapper
public interface StoredProceduresMapper extends BaseMapper {

    /**
     * 销售员销售现况
     * 默认排序：物料编码（升序）-销售员产品销售总额（降序）
     * @return
     */
    @Select({"call past_orders_analysis_1_salesman_selling_condition()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis1SalesmanSellingCondition();

    /**
     * 季度销售现况
     * 默认排序：物料编码（升序）-年（升序）-月（升序）
     * @return
     */
    @Select({"call past_orders_analysis_2_quarterly_selling_condition()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis2QuarterlySellingCondition();

    /**
     * 月度平均比例现况
     * 默认排序：物料编码（升序）
     * @return
     */
    @Select({"call past_orders_analysis_3_month_avg_proportion()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis3MonthAvgProportion();

    /**
     * 符合条件的中间文件2
     * 默认排序：订单id（可理解为没排序）
     * @return
     */
    @Select({"call past_orders_analysis_4_temp2_eligible_orders()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis4Temp2EligibleOrders();

    /**
     * 不符合条件的中间文件3
     * @return
     */
    @Select({"call past_orders_analysis_5_temp3_not_eligible_orders()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis5Temp3NotEligibleOrders();

    /**
     * 替换后的销售员销售现况
     * @return
     */
    @Select({"call past_orders_analysis_6_salesman_selling_condition_replaced()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis6SalesmanSellingConditionReplaced();

    /**
     * 替换后的季度销售现况
     * @return
     */
    @Select({"call past_orders_analysis_7_quarterly_selling_condition_replaced()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis7QuarterlySellingConditionReplaced();

    /**
     * 主要客户现况
     * @return
     */
    @Select({"call past_orders_analysis_8_major_customers_situation()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis8MajorCustomersSituation();

    /**
     * 销售员用产品维度现况
     * @return
     */
    @Select({"call past_orders_analysis_9_product_dimension_situation()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis9ProductDismensionSituation();

    /**
     * 散户现况
     * @return
     */
    @Select({"call past_orders_analysis_10_retail_condition()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis10RetailCondition();

    /**
     * 散户季度现况
     * @return
     */
    @Select({"call past_orders_analysis_11_retail_quarterly_selling_condition()"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String, Object>> doPastOrdersAnalysis11RetailQuarterlySellingCondtion();

    /**
     * 需求表12：客户类型分类-订单版（替换后）
     * @param yearly
     * @param monthly
     * @param agent
     * @param newCustomer
     * @param temporaryCustomer
     * @param daily
     * @return
     */
    @Select({"call past_orders_analysis_12_customer_type_orders(#{yearly,mode=IN}," +
            "#{monthly,mode=IN},#{agent,mode=IN},#{newCustomer,mode=IN},#{temporaryCustomer,mode=IN},#{daily,mode=IN})"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis12CustomerTypeOrders(@Param("yearly")int yearly,
                                                                      @Param("monthly")int monthly,@Param("agent")int agent,
                                                                      @Param("newCustomer")int newCustomer,@Param("temporaryCustomer")int temporaryCustomer,
                                                                      @Param("daily")int daily);

    /**
     * 需求表13：客户类型分类-订单版（已还原）
     * @param yearly
     * @param monthly
     * @param agent
     * @param newCustomer
     * @param temporaryCustomer
     * @param daily
     * @return
     */
    @Select({"call past_orders_analysis_13_customer_type_orders_back(#{yearly,mode=IN}," +
            "#{monthly,mode=IN},#{agent,mode=IN},#{newCustomer,mode=IN},#{temporaryCustomer,mode=IN},#{daily,mode=IN})"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis13CustomerTypeOrdersBack(@Param("yearly")int yearly,
                                                                      @Param("monthly")int monthly,@Param("agent")int agent,
                                                                      @Param("newCustomer")int newCustomer,@Param("temporaryCustomer")int temporaryCustomer,
                                                                      @Param("daily")int daily);

    /**
     * 客户类型分类-月份版（替换后）
     * @param yearly
     * @param monthly
     * @param agent
     * @param newCustomer
     * @param temporaryCustomer
     * @param daily
     * @return
     */
    @Select({"call past_orders_analysis_14_customer_type_monthly(#{yearly,mode=IN}," +
            "#{monthly,mode=IN},#{agent,mode=IN},#{newCustomer,mode=IN},#{temporaryCustomer,mode=IN},#{daily,mode=IN})"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis14CustomerTypeMonthly(@Param("yearly")int yearly,
                                                                          @Param("monthly")int monthly,@Param("agent")int agent,
                                                                          @Param("newCustomer")int newCustomer,@Param("temporaryCustomer")int temporaryCustomer,
                                                                          @Param("daily")int daily);

    /**
     * 客户类型分类-月份版（已还原）
     * @param yearly
     * @param monthly
     * @param agent
     * @param newCustomer
     * @param temporaryCustomer
     * @param daily
     * @return
     */
    @Select({"call past_orders_analysis_15_customer_type_monthly_back(#{yearly,mode=IN}," +
            "#{monthly,mode=IN},#{agent,mode=IN},#{newCustomer,mode=IN},#{temporaryCustomer,mode=IN},#{daily,mode=IN})"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String,Object>> doPastOrdersAnalysis15CustomerTypeMonthlyBack(@Param("yearly")int yearly,
                                                                       @Param("monthly")int monthly,@Param("agent")int agent,
                                                                       @Param("newCustomer")int newCustomer,@Param("temporaryCustomer")int temporaryCustomer,
                                                                       @Param("daily")int daily);

    @Select({"call reload_past_orders_analysis_temp_tables(#{daysLine,mode=IN},#{paramMonthAvgProportion,mode=IN}," +
            "#{timesLine,mode=IN})"})
    @Options(statementType = StatementType.CALLABLE)
    List<Map<String, Object>> doReloadPastOrdersAnalysisTempTables(@Param("daysLine")int daysLine, @Param("paramMonthAvgProportion")double paramMonthAvgProportion,
                                                                   @Param("timesLine")int timesLine);

}
