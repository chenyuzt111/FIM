package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月04 10:17
 * 描 述： TODO
 */
@Data
@TableName("customer_type_orders_replaced")
public class CustomerTypeOrdersReplaced {
    @TableId("order_id")
    private Long orderId;
    @TableField("order_code")
    private String orderCode;
    @TableField("item_code")
    private String itemCOde;
    @TableField("item_name")
    private String itemName;
    @TableField("customer_name")
    private String customerName;
    @TableField("salesman_name")
    private String salesmanName;
    @TableField("sale_num")
    private Long saleNum;
    @TableField("sale_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date saleTime;
    @TableField("customer_type")
    private String customerType;
}
