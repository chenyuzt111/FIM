package com.benewake.saleordersystem.entity.Past;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年06月30 11:17
 * 描 述： TODO
 */
@Data
@TableName("fim_past_prediction_company_table")
public class PastPredictionCompany {
    @TableField("customer_name")
    private String customerName;

    @TableField("remark")
    private String remark;

}
