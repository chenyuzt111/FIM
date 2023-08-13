package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月15 09:27
 * 描 述： TODO
 */
@Data
@TableName("delivery_table")
public class Delivery {
    @TableId(value = "deliveryId",type = IdType.AUTO)
    private Long deliveryId;
    @TableField("delivery_code")
    private String deliveryCode;
    @TableField("delivery_state")
    private Integer deliveryState;
    @TableField("inquiry_code")
    private String inquiryCode;
    @TableField("receive_time")
    private Date receiveTime;
    @TableField("delivery_latest_state")
    private String deliveryLastestState;
    @TableField("delivery_phone")
    private String deliveryPhone;
}
