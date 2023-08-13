package com.benewake.saleordersystem.entity.api.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Lcs
 * 描述：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayResult {

    private String code;

    private String msg;

    private List<Holiday> data;
}
