package com.benewake.saleordersystem.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lcs
 * @since 2023年07月05 17:47
 * 描 述： 筛选条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCriteria {
    String colName;
    String condition;
    Object value;
}
