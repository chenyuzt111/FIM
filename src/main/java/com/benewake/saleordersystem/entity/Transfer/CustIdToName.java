package com.benewake.saleordersystem.entity.Transfer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月03 14:48
 * 描 述： TODO
 */
@Data
public class CustIdToName {
    @ExcelProperty("客户ID")
    private String FCustId;

    @ExcelProperty("客户名")
    private String FName;
}
