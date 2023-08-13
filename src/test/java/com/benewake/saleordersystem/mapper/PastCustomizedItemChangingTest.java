package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年06月30 10:42
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class PastCustomizedItemChangingTest {

    @Autowired
    PastCustomizedItemChangingMapper pastCustomizedItemChangingMapper;

    @Test
    public void getList(){
        pastCustomizedItemChangingMapper.selectList(null).forEach(System.out::println);
    }
}
