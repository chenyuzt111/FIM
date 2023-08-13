package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 10:32
 * 描 述： TODO
 */
@Data
@TableName("fim_past_customer_rename_table")
public class PastCustomerRename {
    @TableId("customer_name_old")
    private String oldName;

    @TableField("customer_name_new")
    private String newName;
}
