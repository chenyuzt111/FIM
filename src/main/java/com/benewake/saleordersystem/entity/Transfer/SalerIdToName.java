package com.benewake.saleordersystem.entity.Transfer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月03 15:04
 * 描 述： TODO
 */
@Data
public class SalerIdToName {
    @ExcelProperty("销售员ID")
    private String fid;
    @ExcelProperty("销售员")
    private String FName;

}
