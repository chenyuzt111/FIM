package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月04 10:34
 * 描 述： TODO
 */
@Data
@TableName("fim_customer_type_table")
public class CustomerType {
    @TableId("customer_id")
    private Long customerId;
    @TableField("item_id")
    private Long itemId;
    @TableField("customer_type")
    private String customerType;
}
