package com.benewake.saleordersystem;

import com.benewake.saleordersystem.entity.api.holiday.Holiday;
import com.benewake.saleordersystem.mapper.UserMapper;
import com.benewake.saleordersystem.service.DeliveryService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.CommonUtils;
import com.benewake.saleordersystem.utils.api.HolidayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
class SaleOrderSystemApplicationTests implements BenewakeConstants {

    @Autowired
    private UserMapper userMap;

    @Autowired
    DeliveryService deliveryService;

    @Test
    void testAsync() throws InterruptedException {
//        alphaService.alphaPython();
//        System.out.println("下一个任务开始");
//        Thread.sleep(30000);
        String[] pas = {"14021402xieminmin","14021402zhengkaiwen","14021402nielinyun","14021402qijunyun","14021402liushuangshuang",
        "14021402liuyan","14021402lvnan","14021402zhaolu","14021402xiaoshuting","14021402gejing","14021402wangxiaoxi","14021402liuhuanyi",
        "14021402wuleibo","14021403wangli"};
        for (String pa : pas) {
            String code = CommonUtils.generateUUID().substring(0, 5);
            String password = CommonUtils.md5(pa+code);
            System.out.println(code);
            System.out.println(password);
            System.out.println();
        }

    }
    @Test
    void contextLoads() {
        deliveryService.updateDelivery();
    }

    @Test
    void testHoliday() throws InterruptedException {
        List<Holiday> list = HolidayUtils.getMultiHolidays(System.currentTimeMillis(),30,false);
        for(Holiday h : list){
            System.out.println(h.toString());
        }
    }

}
