package com.benewake.saleordersystem.entity.api.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Lcs
 * 描述：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {
    /**
     *当前日期
     */
    private String date;

    /**
     *当前周第几天 1-周一 2-周二 ... 7-周日
     */
    private int weekDay;

    /**
     *天干地支纪年法描述 例如：戊戌
     */
    private String yearTips;

    /**
     *类型 0 工作日 1 假日 2 节假日 如果ignoreHoliday参数为true，这个字段不返回
     */
    private int type;

    /**
     *	类型描述 比如 国庆,休息日,工作日 如果ignoreHoliday参数为true，这个字段不返回
     */
    private String typeDes;

    /**
     *	属相 例如：狗
     */
    private String chineseZodiac;

    /**
     *	节气描述 例如：小雪
     */
    private String solarTerms;

    /**
     *农历日期
     */
    private String lunarCalendar;

    /**
     *	宜事项
     */
    private String suit;

    /**
     *	这一年的第几天
     */
    private int dayOfYear;

    /**
     *	这一年的第几周
     */
    private int weekOfYear;

    /**
     *	星座
     */
    private String constellation;

    /**
     *如果当前是工作日 则返回是当前月的第几个工作日，否则返回0 如果ignoreHoliday参数为true，这个字段不返回
     */
    private int indexWorkDayOfMonth;

}
