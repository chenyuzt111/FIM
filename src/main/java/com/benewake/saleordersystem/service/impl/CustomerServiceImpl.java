package com.benewake.saleordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benewake.saleordersystem.entity.Customer;
import com.benewake.saleordersystem.mapper.CustomerMapper;
import com.benewake.saleordersystem.service.CustomerService;
import com.kingdee.bos.webapi.entity.QueryParam;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lcs
 * @since 2023年07月03 17:12
 * 描 述： TODO
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private K3CloudApi api;

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public int updateCustomerDB() {
        // 查询条件
//        QueryParam queryParam = new QueryParam();
//        queryParam.setFormId("BD_Customer");
//        queryParam.setFieldKeys("FCustId,FName");
//        int ans = 0;
//        try {
//            List<Customer> res = api.executeBillQuery(queryParam,Customer.class);
//            ans = customerMapper.updateCustomer(res);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return ans;
        return 0;
    }

    @Override
    public List<Customer> getCustomerLikeList(String customerName) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Customer::getFCustId,Customer::getFName);
        queryWrapper.like(StringUtils.isNotBlank(customerName),Customer::getFName,customerName);
        return customerMapper.selectList(queryWrapper);
    }

    @Override
    public Customer findCustomerById(Long customerId) {
        return customerMapper.selectById(customerId);
    }

    @Override
    public Customer findCustomerByName(String customerName) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getFName,customerName);
        return customerMapper.selectOne(queryWrapper);
    }

    @Override
    public List<String> getCustomerTypeLikeList(String type) {
        return customerMapper.getCustomerTypeLikeList("%"+type+"%");
    }
}
