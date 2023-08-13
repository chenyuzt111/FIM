package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 11:24
 * 描 述： TODO
 */
@Data
@TableName("fim_salesman_table")
public class Salesman {
    @TableId(value = "salesman_id",type = IdType.AUTO)
    private Long Id;
    @TableField("salesman_name")
    private String salesmanName;
}
