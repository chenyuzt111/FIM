package com.benewake.saleordersystem.entity.Past;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月11 10:43
 * 描 述： TODO
 */
@Data
@TableName("fim_past_orders_table")
public class SaleOut {
    @TableId("order_id")
    private Long orderId;
//    @ExcelProperty("单据编号")
//    private String FBillNo;
    @ExcelProperty("物料编码")
    @TableField("item_code")
    private String FMaterialID;
    @ExcelProperty("物料名称")
    private String FMaterialName;
    @ExcelProperty("销售数量")
    @TableField("sale_num")
    private String FRealQty;
    @ExcelIgnore
    private String FStockOrgId;
    @ExcelProperty("运输单号")
    private String FCarriageNO;
    @ExcelProperty("收件人电话")
    private String F_ora_Text2;
    @ExcelProperty("客户名称")
    @TableField("customer_name")
    private String FCustomerID;
    @ExcelProperty("销售员")
    @TableField("salesman_name")
    private String FSalesManID;
    @ExcelProperty("销售日期")
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("sale_time")
    private String FDate;
    @ExcelProperty("订单单号")
    @TableField("order_code")
    private String FSoorDerno;

    @ExcelProperty("fim单据编号")
    private String FIM;
}
