package com.benewake.saleordersystem.entity.sfexpress;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月05 15:29
 * 描 述： 顺丰路由中的运送状态信息
 */
@Data
public class Route {
    private String acceptAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date acceptTime;
    private String remark;
    private String opCode;
}
