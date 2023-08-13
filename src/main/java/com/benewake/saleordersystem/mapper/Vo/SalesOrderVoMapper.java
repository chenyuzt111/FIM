package com.benewake.saleordersystem.mapper.Vo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
@Mapper
public interface SalesOrderVoMapper extends BaseMapper<Map<String,Object>> {

    @Select("<script>" +
            "select * from fim_item_table " +
            "${ew1.customSqlSegment} " +
            "limit #{offset},#{limit} " +
            "</script>")
    List<Map<String,Object>> getALL(@Param("ew1") Wrapper wrapper,@Param("offset") int offset,@Param("limit") int limit);

    /**
     * 根据筛选条件获取订单数据
     * @param wrapper1
     * @param wrapper2
     * @param wrapper3
     * @param wrapper4
     * @param wrapper5
     * @param wrapper6
     * @param wrapper7
     * @return
     */
    @Select("<script>" +
            "select distinct (inquiry_id),inquiry_code,inquiry_init_type,state,created_user_name,item_type,inquiry_type," +
            "item_code,item_name,customer_name,salesman_name,sale_num,expected_time,arranged_time,delay," +
            "customer_type,order_delivery_progress,delivery_code,receive_time,delivery_state,customize,remark " +
            "from " +
            "(select inquiry_id as inquiry_id,(case when state=-1 then '无效' when state=0 then '保存' else concat('询单',state,'次') end) as state," +
            "a.inquiry_code as inquiry_code,sale_num as sale_num,expected_time as expected_time," +
            "arranged_time as arranged_time,created_user as created_user,salesman_id as salesman_id,a.item_id as item_id," +
            "a.customer_id as customer_id,remark as remark,(case " +
            "when order_delivery_progress is null then '未发货' " +
            "when order_delivery_progress = 80 then '已签收' " +
            "else '已发货客户未签收' end) as order_delivery_progress,delivery_code,receive_time,delivery_state,delay as delay," +
            "(case when item_type = 1 then '已有标品' when item_type = 2 then '已有定制' " +
            "when item_type = 3 then '新增软件定制' when item_type = 4 then '新增原材料定制' " +
            " when item_type = 5 then '新增原材料+软件定制' else '错误物料类型' end) as item_type," +
            "item_code as item_code,item_name as item_name,(case when inquiry_type = 1 then 'PO(客户付款)' " +
            "when inquiry_type = 2 then 'PR(客户提出付款意向)' when inquiry_type = 3 then 'YG(供应链预估)' " +
            "when inquiry_type = 4 then 'YC(销售预测)' when inquiry_type = 5 then 'XD(意向询单)' else 'ER(错误)' end) " +
            "as inquiry_type,(case when item_type = 1 or item_type = 2 then '否' " +
            "when item_type = 3 or item_type = 4 or item_type = 5 then '是' else '错误' end) as customize," +
            "(case when customer_type is null then '日常' else customer_type end) as customer_type," +
            "(case when inquiry_init_type = 5 then '销售询单' when inquiry_init_type = 4 then '销售预测' " +
            "when inquiry_init_type = 3 then '供应链预估' else '错误的初始类型' end) as inquiry_init_type " +
            "from " +
            "(select fim_inquiry_table.inquiry_id,inquiry_code as inquiry_code,state as state,inquiry_init_type as inquiry_init_type," +
            "inquiry_type as inquiry_type,sale_num as sale_num,expected_time as expected_time," +
            "arranged_time as arranged_time,created_user as created_user,salesman_id as salesman_id," +
            "item_id as item_id,customer_id as customer_id,remark as remark," +
            "(case when arranged_time > expected_time then '是' else '否' end )as delay " +
            "from fim_inquiry_table " +
            "left join " +
            "(select SUBSTRING_INDEX(GROUP_CONCAT(inquiry_id ORDER BY created_time desc),',',1) as inquiry_id," +
            "SUBSTRING_INDEX(GROUP_CONCAT(inquiry_type ORDER BY created_time asc),',',1) as inquiry_init_type " +
            "from fim_inquiry_table " +
            "group by inquiry_code) as bb on bb.inquiry_id = fim_inquiry_table.inquiry_id " +
            "${qw1.customSqlSegment}" +
            ") as a " +
            "left join (select customer_id,item_id,customer_type " +
            "from fim_customer_type_table) as d on a.customer_id = d.customer_id and d.item_id = a.item_id " +
            "left join " +
            "(select item_id,item_code,item_name,item_type " +
            "from fim_item_table "+
            "${qw2.customSqlSegment}" +
            ") as e on e.item_id = a.item_id " +
            "left join" +
            "(select inquiry_code,delivery_state as order_delivery_progress,delivery_code as delivery_code," +
            "receive_time as receive_time,delivery_latest_state as delivery_state " +
            "from delivery_table) as j on j.inquiry_code = a.inquiry_code " +
            "${qw3.customSqlSegment}" +
            ") as a " +
            "left join " +
            "(select FIM_user_id,FIM_user_name as salesman_name " +
            "from fim_users_table " +
            "${qw4.customSqlSegment}" +
            ") as b on a.salesman_id = b.FIM_user_id " +
            "left join " +
            "(select FIM_user_id,FIM_user_name as created_user_name " +
            "from fim_users_table " +
            "${qw5.customSqlSegment}" +
            ") as c on a.created_user = c.FIM_user_id " +
            "left join " +
            "(select customer_id,customer_name " +
            "from fim_customer_table " +
            "${qw6.customSqlSegment}" +
            ") as f on a.customer_id = f.customer_id " +
            "${ew.customSqlSegment} " +
            "order by inquiry_code desc " +
            "</script>")
    List<Map<String,Object>> selectListByFilter(@Param("qw1") Wrapper wrapper1,@Param("qw2")Wrapper wrapper2,
                                                @Param("qw3")Wrapper wrapper3,@Param("qw4")Wrapper wrapper4,
                                                @Param("qw5")Wrapper wrapper5,@Param("qw6")Wrapper wrapper6,
                                                @Param("ew")Wrapper wrapper7);
}
