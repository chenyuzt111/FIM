package com.benewake.saleordersystem.entity.Transfer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月03 15:26
 * 描 述： TODO
 */
@Data
public class CreateIdToName {
    @ExcelProperty("创建人Id")
    private String FUserID;

    @ExcelProperty("创建人")
    private String FName;
}
