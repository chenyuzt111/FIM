package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 10:44
 * 描 述： TODO
 */
@Data
@TableName("fim_past_item_change_table")
public class PastItemChange {

    @TableId("item_code_old")
    private String oldItemCode;

    @TableField("item_code_new")
    private String newItemCode;

}
