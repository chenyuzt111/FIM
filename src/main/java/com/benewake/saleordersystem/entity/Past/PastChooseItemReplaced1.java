package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年06月30 10:18
 * 描 述： TODO
 */
@Data
@TableName("fim_past_choose_item_table_replaced1")
public class PastChooseItemReplaced1 {
    @TableId("item_code")
    private String itemCode;

    @TableField("item_name")
    private String itemName;

    @TableField("start_month")
    @JsonFormat(pattern = "yyyy/MM", timezone = "GMT+8")
    private Date startMonth;
}
