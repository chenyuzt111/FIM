package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月06 17:26
 * 描 述： TODO
 */
@Data
@TableName("fim_view_col_table")
public class ViewCol {
    @TableId(value = "col_id",type = IdType.AUTO)
    private Long colId;
    @TableField("value_operator")
    private String valueOperator;
    @TableField("col_value")
    private String colValue;
    @TableField("view_id")
    private Long viewId;
    @TableField("col_seq")
    private Integer colSeq;
}
