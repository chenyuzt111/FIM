package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年06月30 10:58
 * 描 述： TODO
 */
@Data
@TableName("fim_past_orders_temp3_table")
public class PastOrdersTemp3 {
    @TableId("order_id")
    private Long id;

    @TableField("order_code")
    private String orderCode;
    @TableField("item_code")
    private String itemCode;
    @TableField("customer_name")
    private String customerName;
    @TableField("salesman_name")
    private String salesmanName;
    @TableField("sale_num")
    private Long saleNum;
    @TableField("sale_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date saleTime;
}
