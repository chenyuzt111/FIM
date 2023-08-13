package com.benewake.saleordersystem.mapper;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年06月30 10:08
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class StoredProceduresTest {
    @Autowired
    StoredProceduresMapper storedProceduresMapper;

    @Test
    public void doGet(){
        List<Map<String,Object>> list = storedProceduresMapper.doPastOrdersAnalysis13CustomerTypeOrdersBack(1,1,1,1,1,1);
        for(Map<String,Object> t : list){
            t.forEach((k,v)-> System.out.print(k+"："+v+","));
            System.out.println();
        }
    }
}
