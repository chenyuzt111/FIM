package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.Inquiry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lcs
 */
@Mapper
public interface DeliveryMapper extends BaseMapper<Delivery> {

    /**
     * 批量更新运输状态信息
     * @param deliveryList
     * @return
     */
    @Update("<script>" +
            "<foreach collection='list' item='item' index='index' separator=';'>" +
            "update delivery_table " +
            "<set>" +
            "delivery_state = #{item.deliveryState} " +
            "<if test='item.receiveTime!=null'> " +
            ",receive_time = #{item.receiveTime} " +
            "</if> " +
            "<if test='item.deliveryLastestState!=null'> " +
            ",delivery_latest_state = #{item.deliveryLastestState} " +
            "</if> " +
            "</set> " +
            "where inquiry_code = #{item.inquiryCode} " +
            "</foreach> " +
            "</script>")
    int updateDeliveriesState(List<Delivery> deliveryList);

    @Update("<script>" +
            "<foreach collection='list' item='item' index='index' separator=';'>" +
            "update delivery_table " +
            "<set>" +
            "delivery_code = #{item.deliveryCode}, " +
            "delivery_phone = #{item.deliveryPhone} " +
            "</set> " +
            "where inquiry_code = #{item.inquiryCode} " +
            "</foreach> " +
            "</script>")
    int updateDeliveriesCode(List<Delivery> deliveryList);


    /**
     * 批量插入数据
     * @param inquiries
     * @return
     */
    @Insert("<script>" +
            "insert into delivery_table(" +
            "inquiry_code" +
            ") values " +
            "<foreach collection='lists' item='list' separator=','> " +
            "(" +
            "#{list.inquiryCode}" +
            ")" +
            "</foreach>" +
            "</script>")
    int insertLists(@Param("lists") List<Inquiry> inquiries);

    /**
     * 获取没有运输单号的单据编号列表
     * @param userId
     * @return
     */
    @Select("<script>" +
            "select inquiry_code " +
            "from delivery_table " +
            "where inquiry_code in(" +
            "select inquiry_code from fim_inquiry_table " +
            "where (created_user = #{userId} or salesman_id = #{userId}) and state >= 0" +
            ") and (" +
            "delivery_code is null or delivery_code = '' or delivery_phone is null " +
            "or delivery_phone = ''" +
            ")" +
            "</script>")
    List<Delivery> selectUnFindDeliveriesByUser(@Param("userId") Long userId);

    /**
     * 获取用户所有未签收的运输单号和电话号码
     * @param userId
     * @return
     */
    @Select("<script>" +
            "select inquiry_code,delivery_code,delivery_phone " +
            "from delivery_table " +
            "where inquiry_code in(" +
            "select inquiry_code from fim_inquiry_table " +
            "where (created_user = #{userId} or salesman_id = #{userId}) and state >= 0 " +
            ") and " +
            "delivery_code is not null and delivery_phone is not null " +
            "and receive_time is null " +
            "</script>")
    List<Delivery> selectUnFinisheDeliveriesByUser(@Param("userId")Long userId);

    @Select("<script>" +
            "select delivery_latest_state from delivery_table " +
            "where delivery_latest_state like #{deliveryState} " +
            "</script>")
    List<String> getDeliveryStateList(@Param("deliveryState") String deliveryState);
}
