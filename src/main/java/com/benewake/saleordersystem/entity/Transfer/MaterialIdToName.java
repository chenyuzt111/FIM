package com.benewake.saleordersystem.entity.Transfer;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Lcs
 * @since 2023年07月03 15:54
 * 描 述： TODO
 */
@Data
public class MaterialIdToName {
    @ExcelProperty("物料ID")
    private String FMaterialId;
    @ExcelProperty("物料编码")
    private String FNumber;
}
