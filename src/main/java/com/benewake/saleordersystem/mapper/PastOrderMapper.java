package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.Past.PastOrder;
import com.benewake.saleordersystem.entity.Past.SaleOut;
import com.benewake.saleordersystem.entity.Past.Withdraw;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lcs
 */
@Mapper
public interface PastOrderMapper {

    /**
     * 删除当前所有数据并导入本地历史订单数据
     * 描 述：从金蝶获取最新数据后调用
     */
    @Select("call FIM_past_orders_table()")
    void reloadLocalOrders();

    /**
     * 添加从金蝶取的数据
     * @param pastOrders
     * @return
     */
    @Insert("<script> " +
            "Insert into fim_past_orders_table(order_code,item_code,customer_name," +
            "salesman_name,sale_num,sale_time) " +
            "values " +
            "<foreach collection='lists' item='list' separator=','> " +
            "(" +
            "#{list.FSoorDerno},#{list.FMaterialID},#{list.FCustomerID},#{list.FSalesManID}," +
            "#{list.FRealQty},#{list.FDate}" +
            ")" +
            "</foreach>" +
            "</script>")
    int insertPastOrders(@Param("lists") List<PastOrder> pastOrders);


}
