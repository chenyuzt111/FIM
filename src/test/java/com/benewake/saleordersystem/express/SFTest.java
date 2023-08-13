package com.benewake.saleordersystem.express;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.sfexpress.SF_SEARCH_RESULT;
import com.benewake.saleordersystem.service.KingDeeService;
import com.benewake.saleordersystem.service.SFExpressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年07月05 14:33
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class SFTest {
    @Autowired
    SFExpressService sfExpressService;
    @Autowired
    KingDeeService kingDeeService;

    @Test
    public void testSF(){
        SF_SEARCH_RESULT result = sfExpressService.findRoutesByCode("SF1420610891305","1917");
        System.out.println(result.toString());
    }

    @Test
    public void unionTest() throws Exception {
//        Route route = sfExpressService.getLastestRouteByFCarriageNO("XSDD2306020");
//        if(route==null) System.out.println("未发货或无收件人电话");
//        else System.out.println(route.toString());
    }
}
