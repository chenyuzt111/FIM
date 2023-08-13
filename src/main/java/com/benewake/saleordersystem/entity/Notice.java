package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年08月02 16:26
 * 描 述： TODO
 */
@Data
@TableName("fim_notice_table")
public class Notice {
    @TableId("id")
    private Long id;
    @TableField("create_user_id")
    private Long createUserId;
    @TableField("message")
    private String message;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date updateTime;
    @TableField("is_deleted")
    private Integer deleted;
    @TableField("type")
    private Integer type;
}
