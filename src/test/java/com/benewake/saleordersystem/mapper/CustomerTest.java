package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.Customer;
import com.benewake.saleordersystem.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * @author Lcs
 * @since 2023年07月03 17:35
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class CustomerTest {

    @Autowired
    private CustomerService customerService;


    @Test
    public void testUpdateDB(){
        customerService.updateCustomerDB();
    }

    @Test
    public void testLikeList(){
        customerService.getCustomerLikeList("大学").forEach(System.out::println);

    }


}
