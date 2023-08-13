package com.benewake.saleordersystem.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.localdatetime.LocalDateTimeDateConverter;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lcs
 * @since 2023年07月09 10:15
 * 描 述： TODO
 */
@Data
public class InquiryModel {
    @ExcelProperty(value = "订单类型",index = 0)
    private String inquiryType;
    @ExcelProperty(value = "物料编码",index = 1)
    private String itemCode;
    @ExcelProperty(value = "物料名称",index = 2)
    private String itemName;
    @ExcelProperty(value = "数量",index = 3)
    private String num;
    @ExcelProperty(value = "客户名称",index = 4)
    private String customerName;
    @ExcelProperty(value = "销售员",index = 5)
    private String salesmanName;
    @ExcelProperty(value = "产品类型",index = 6)
    private String itemType;
    @ExcelProperty(value = "客户类型",index = 7)
    private String customerType;
    @ExcelProperty(value = "期望发货日期",index = 8)
    private String exceptedTime;
    @ExcelProperty(value = "计划反馈日期",index = 9)
    private String arrangedTime;
    @ExcelProperty(value = "是否延期",index = 10)
    private String delay;
    @ExcelProperty(value = "备注",index = 11)
    private String remark;
}
