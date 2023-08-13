package com.benewake.saleordersystem.utils.api;

import com.alibaba.fastjson.JSON;
import com.benewake.saleordersystem.entity.api.holiday.Holiday;
import com.benewake.saleordersystem.entity.api.holiday.HolidayResult;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.HttpUtils;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author Lcs
 */
public class HolidayUtils implements BenewakeConstants {

    private static final String APP_ID = "hivibjqlwkqfuprt";

    private  static final String APP_SECRET = "a01sZW1RSjdQQjNxZmEvMktPV2ZJUT09";

    /**
     * @param date          日期 格式 yyyyMMdd
     * @param ignoreHoliday 是否忽略节假日，仅仅获取万年历，默认值false
     * @return 日期
     */
    @SneakyThrows
    public static Holiday getHoliday(Date date, boolean ignoreHoliday) {
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        HashMap<String, String> bodys = new HashMap<>();
        bodys.put("ignoreHoliday","false");
        bodys.put("app_id", APP_ID);
        bodys.put("app_secret", APP_SECRET);
        String scheme = "https";
        String host = "www.mxnzp.com";
        String path = "/api/holiday/single/" + sdf.format(date);
        HttpResponse httpResponse = HttpUtils.doGet(scheme, host, path, bodys, null);
        String holidayJson = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(holidayJson);
        //拼接请求地址
        HolidayResult holidayResult = JSON.parseObject(holidayJson, HolidayResult.class);
        return holidayResult.getData().get(0);
    }

    /**
     * @param holiday          日期
     * @return 按照公司规定，周日休息，节假日休息 其他时间不休息
     */
    @SneakyThrows
    public static boolean isWorkDay(Holiday holiday) {
        return holiday.getWeekDay() != 7 || holiday.getType() == 0;
    }

    @SneakyThrows
    public static List<Holiday> getMultiHolidays(Long startTime,int needDay,boolean ignoreHoliday){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        List<Holiday> ans = new ArrayList<>(needDay);
        if(needDay == 1){
            ans.add(getHoliday(new Date(startTime),ignoreHoliday));
            return ans;
        }
        StringBuilder args = new StringBuilder(sdf.format(new Date(startTime)));
        for(int i=1;i<needDay;++i){
            args.append(",").append(sdf.format(new Date(startTime + (long) ONE_DAY * i)));
        }
        HashMap<String, String> bodys = new HashMap<>();
        bodys.put("ignoreHoliday","false");
        bodys.put("app_id", APP_ID);
        bodys.put("app_secret", APP_SECRET);
        String scheme = "https";
        String host = "www.mxnzp.com";
        String path = "/api/holiday/multi/" + args;
        HttpResponse httpResponse = HttpUtils.doGet(scheme, host, path, bodys, null);
        String holidayJson = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(holidayJson);
        //拼接请求地址
        HolidayResult holidayResult = JSON.parseObject(holidayJson, HolidayResult.class);
        return holidayResult.getData();
    }
}
