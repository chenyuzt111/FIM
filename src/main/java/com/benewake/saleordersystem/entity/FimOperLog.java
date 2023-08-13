package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年08月05 14:58
 * 描 述： TODO
 */
@Data
@TableName("fim_oper_log")
public class FimOperLog {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    @TableField("title")
    private String title;
    @TableField("business_type")
    private String businessType;
    @TableField("oper_name")
    private String operName;
    @TableField("oper_ip")
    private String operIp;
    @TableField("status")
    private Integer status;
    @TableField("error_msg")
    private String errorMsg;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("is_deleted")
    private Integer deleted;

}
