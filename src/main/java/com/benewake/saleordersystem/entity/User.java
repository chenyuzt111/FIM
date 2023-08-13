package com.benewake.saleordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Lcs
 */
@Data
@TableName("fim_users_table")
public class User {
    @TableId(value = "FIM_user_id",type = IdType.AUTO)
    private Long id;
    @TableField("FIM_user_name")
    private String username;
    @TableField("FIM_user_password")
    private String password;
    @TableField("FIM_coding")
    private String salt;
    @TableField("FIM_user_type")
    private Long userType;
    @TableField("FIM_collection_func")
    private Long userConllection;
//    //激活码
//    @TableField("activation_code")
//    private String activationCode;
//    //头像路径
//    @TableField("header_url")
//    private String headerUrl;
//    //日期
//    @TableField("create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createTime;
}
