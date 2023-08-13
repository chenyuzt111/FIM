package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 10:38
 * 描 述： TODO
 */
@Data
@TableName("fim_past_customized_item_changing_table")
public class PastCustomizedItemChanging {
    @TableId("customer_name")
    private String customerName;

    @TableField("item_code_old")
    private String oldItemCode;

    @TableField("item_code_new")
    private String newItemCode;


}
