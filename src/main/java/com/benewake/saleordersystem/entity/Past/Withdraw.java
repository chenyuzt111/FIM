package com.benewake.saleordersystem.entity.Past;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月11 14:27
 * 描 述： TODO
 */
@Data
@TableName("fim_past_orders_table")
public class Withdraw {
    @TableId("order_id")
    private Long orderId;
    @ExcelProperty("物料编码")
    @TableField("item_code")
    private String FMaterialId;
    @ExcelProperty("物料名称")
    private String FMaterialName;
    @ExcelProperty("销售数量")
    @TableField("sale_num")
    private String FRealQty;
//    @ExcelIgnore
//    private String FSaleOrgId;
    @ExcelProperty("客户名称")
    @TableField("customer_name")
    private String FRetcustId;
    @ExcelProperty("销售员")
    @TableField("salesman_name")
    private String FSalesManId;
    @ExcelProperty("销售日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("sale_time")
    private String FDate;
    @ExcelProperty("订单单号")
    @TableField("order_code")
    private String FOrderNo;


}
