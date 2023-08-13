package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月08 14:05
 * 描 述： TODO
 */
@Data
@TableName("fim_inquiry_code_table")
public class InquiryCode {
    @TableField("inquiry_type")
    private Long inquiryType;
    @TableField("today_date")
    private Date todayDate;
    @TableField("inquiry_num")
    private Long inquiryNum;
}
