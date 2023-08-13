package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年06月30 10:51
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class PastOrdersCleanTempTest {
    @Autowired
    PastOrdersCleanTempMapper pastOrdersCleanTempMapper;

    @Autowired
    PastOrdersTemp1Mapper pastOrdersTemp1Mapper;
    @Autowired
    PastOrdersTemp2Mapper pastOrdersTemp2Mapper;
    @Autowired
    PastOrdersTemp3Mapper pastOrdersTemp3Mapper;
    @Autowired
    PastOrdersTemp4Mapper pastOrdersTemp4Mapper;

    @Test
    public void getList(){
        pastOrdersCleanTempMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void getList1(){
        pastOrdersTemp1Mapper.selectList(null).forEach(System.out::println);
    }
    @Test
    public void getList2(){
        pastOrdersTemp2Mapper.selectList(null).forEach(System.out::println);
    }
    @Test
    public void getList3(){
        pastOrdersTemp3Mapper.selectList(null).forEach(System.out::println);
    }
    @Test
    public void getList4(){
        pastOrdersTemp4Mapper.selectList(null).forEach(System.out::println);
    }
}
