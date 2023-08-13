package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年07月04 10:25
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class CustomerTypeOrdersTest {
    @Autowired
    CustomerTypeOrdersBackMapper customerTypeOrdersBackMapper;
    @Autowired
    CustomerTypeOrdersReplaceMapper customerTypeOrdersReplaceMapper;

    @Test
    public void testBack(){
        customerTypeOrdersBackMapper.selectList(null).forEach(System.out::println);
    }
    @Test
    public void testReplace(){
        customerTypeOrdersReplaceMapper.selectList(null).forEach(System.out::println);
    }

}
