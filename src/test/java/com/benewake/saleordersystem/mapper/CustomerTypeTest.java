package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.service.CustomerTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年07月04 10:36
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class CustomerTypeTest {
    @Autowired
    CustomerTypeMapper customerTypeMapper;

    @Autowired
    CustomerTypeService customerTypeService;
    @Test
    public void testList(){
        customerTypeMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void testGetCustomerType(){
        System.out.println(customerTypeService.getCustomerTypeByRule(7L,39L));
    }
}
