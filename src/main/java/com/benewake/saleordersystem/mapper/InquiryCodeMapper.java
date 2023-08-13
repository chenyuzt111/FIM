package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.InquiryCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lcs
 */
@Mapper
public interface InquiryCodeMapper extends BaseMapper<InquiryCode> {

    /**
     * 获取已有前缀的最大值
     * @param
     * @return
     */
    @Select("<script>" +
            "select inquiry_num from fim_inquiry_code_table " +
            "where inquiry_type = #{type} and today_date = #{date}" +
            "</script>")
    Long getMonth(@Param("type")Long type,@Param("date")String date);
    /**
     * 更新当前前缀最大值
     */
    @Insert("<script>" +
            "Replace fim_inquiry_code_table values " +
            "(#{type},#{num},#{month})" +
            "</script>")
    int updateMaxMonthString(@Param("month")String month,@Param("num")Long num,@Param("type")Long type);
}
