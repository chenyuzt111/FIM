package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    @Insert("<script> " +
            "Replace into fim_customer_table values " +
            "<foreach collection='customers' item='customer' separator=','> " +
            "(" +
            "#{customer.FCustId},#{customer.FName}" +
            ")" +
            "</foreach>" +
            "</script>")
    int updateCustomer(@Param("customers") List<Customer> customers);

    @Select("<script>" +
            "Select customer_type from customer_type_dic " +
            "where customer_type like #{type} " +
            "</script>")
    List<String> getCustomerTypeLikeList(@Param("type") String s);
}
