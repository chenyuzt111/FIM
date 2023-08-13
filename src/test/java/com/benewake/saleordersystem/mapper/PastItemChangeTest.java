package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.Past.PastItemChange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lcs
 * @since 2023年06月30 10:45
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class PastItemChangeTest {
    @Autowired
    PastItemChangeMapper pastItemChangeMapper;

    @Test
    public void getList(){
        LambdaQueryWrapper<PastItemChange> q = new LambdaQueryWrapper<>();
        q.select(PastItemChange::getOldItemCode,PastItemChange::getNewItemCode);
        pastItemChangeMapper.selectList(q).forEach(System.out::println);
    }
}
