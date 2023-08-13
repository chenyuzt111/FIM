package com.benewake.saleordersystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lcs
 * @since 2023年08月05 16:18
 * 描 述： 自定义异常模板
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{
    private Integer code;
    private String msg;
}
