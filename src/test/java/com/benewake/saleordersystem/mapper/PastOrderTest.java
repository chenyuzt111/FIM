package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.VO.FilterCriteria;
import com.benewake.saleordersystem.service.PastOrderService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lcs
 * @since 2023年07月04 15:05
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class PastOrderTest implements BenewakeConstants {
    @Autowired
    public PastOrderService pastOrderService;

//    @Test
//    public void testReload(){
//        pastOrderMapper.reloadLocalOrders();
//    }
    @Test
    public void testList(){
        List<FilterCriteria> filters = new ArrayList<>();
        filters.add(new FilterCriteria("salesman_name",EQUAL,"刘双双"));
        filters.add(new FilterCriteria("order_id",GREATER_OR_EQUAL,216));
        filters.add(new FilterCriteria("sale_time",GREATER_OR_EQUAL,"2021-08-17"));
        filters.add(new FilterCriteria("customer_name",LIKE,"A"));


        //alphaService.getList(filters).forEach(System.out::println);
    }

    @Test
    public void testUnion(){
        System.out.println(pastOrderService.savePastOrder(true));
    }
}
