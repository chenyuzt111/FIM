package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月15 16:40
 * 描 述： TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fim_past_orders_table")
public class PastOrder {
    @TableId("order_id")
    private Long orderId;
    @TableField("item_code")
    private String FMaterialID;
    @TableField("sale_num")
    private String FRealQty;
    @TableField("customer_name")
    private String FCustomerID;
    @TableField("salesman_name")
    private String FSalesManID;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("sale_time")
    private Date FDate;
    @TableField("order_code")
    private String FSoorDerno;

    public PastOrder(String FMaterialID, String FRealQty, String FCustomerID, String FSalesManID, Date FDate, String FSoorDerno) {
        this.FMaterialID = FMaterialID;
        this.FRealQty = FRealQty;
        this.FCustomerID = FCustomerID;
        this.FSalesManID = FSalesManID;
        this.FDate = FDate;
        this.FSoorDerno = FSoorDerno;
    }
}
