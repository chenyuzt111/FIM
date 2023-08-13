package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年06月30 10:23
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class pastChooseItemTest {

    @Autowired
    PastChooseItemMapper pastChooseItemTest;
    @Autowired
    PastChooseItemReplaced1Mapper pastChooseItemReplaced1Mapper;
    @Autowired
    PastChooseItemReplaced2Mapper pastChooseItemReplaced2Mapper;
    @Test
    public void selectList(){
        pastChooseItemTest.selectList(null).forEach(v-> System.out.println(v.toString()));
    }

    @Test
    public void selectListr1(){
        pastChooseItemReplaced1Mapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void selectListr2(){
        pastChooseItemReplaced2Mapper.selectList(null).forEach(System.out::println);
    }

}
