package com.benewake.saleordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.benewake.saleordersystem.entity.CustomerType;
import com.benewake.saleordersystem.mapper.CustomerTypeMapper;
import com.benewake.saleordersystem.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lcs
 * @since 2023年07月08 11:36
 * 描 述： TODO
 */
@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeMapper customerTypeMapper;

    @Override
    public String getCustomerTypeByRule(Long customerId, Long itemId) {

        if(customerId==null || itemId==null) {
            return "";
        }

        LambdaQueryWrapper<CustomerType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(CustomerType::getCustomerType)
                .eq(CustomerType::getCustomerId,customerId)
                .eq(CustomerType::getItemId,itemId);

        CustomerType customerType = customerTypeMapper.selectOne(queryWrapper);
        if(customerType == null) {
            return "无匹配的客户类型，请飞书联系管理员";
        }
        return customerType.getCustomerType();
    }
}
