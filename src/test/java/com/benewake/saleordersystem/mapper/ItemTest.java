package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.Item;
import com.benewake.saleordersystem.entity.Past.PastItemChange;

import com.benewake.saleordersystem.mapper.Vo.SalesOrderVoMapper;
import com.benewake.saleordersystem.service.ItemService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年06月29 17:24
 * 描 述： TODO
 */
@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class ItemTest {

    @Autowired
    MybatisPlusInterceptor mybatisPlusInterceptor;
    @Autowired
    PastItemChangeMapper pastItemChangeMapper;

    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemService itemService;

    @Autowired
    SalesOrderVoMapper salesOrderVoMapper;

    @Test
    public void testPage(){
        // 分页测试
        Page<PastItemChange> page = new Page<>(1,4);
        IPage<PastItemChange> iPage = pastItemChangeMapper.selectPage(page,null);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        for(PastItemChange p : iPage.getRecords()){
            System.out.println(p.toString());
        }
    }

    @Test
    public void testItem(){
        QueryWrapper<List<Map<String,Object>>> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("item_name","(整箱包装)");
        List<Map<String,Object>> res = salesOrderVoMapper.getALL(queryWrapper,0,10);
        res.forEach(c->{
            c.forEach((k,v)->{
                System.out.print(k+" "+v+" ");
            });
            System.out.println();
        });
    }

    @Test
    public void testItemLike(){
        val list = itemService.itemCodeLikeList("13.01");
        list.forEach(System.out::println);
    }



}
