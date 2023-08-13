package com.benewake.saleordersystem.entity.api.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: Lcs
 * 描述：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDay {

    /**
     * 工作日id
     */
    private Long workDayId;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    /**
     * 是否是工作日
     */
    private Boolean isWorkDay;

    /**
     * 备注
     */
    private String holidayRemarks;
}
