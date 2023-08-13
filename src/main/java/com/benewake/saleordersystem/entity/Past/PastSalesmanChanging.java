package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 11:22
 * 描 述： TODO
 */
@Data
@TableName("fim_past_salesman_changing_table")
public class PastSalesmanChanging {
    @TableId("salesman_name_old")
    private String oldName;
    @TableField("salesman_name_new")
    private String newName;
}
