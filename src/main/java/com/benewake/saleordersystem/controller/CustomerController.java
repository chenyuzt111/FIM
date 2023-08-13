package com.benewake.saleordersystem.controller;

import com.alibaba.fastjson2.JSONObject;
import com.benewake.saleordersystem.entity.Customer;
import com.benewake.saleordersystem.service.CustomerService;
import com.benewake.saleordersystem.service.CustomerTypeService;
import com.benewake.saleordersystem.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月12 11:15
 * 描 述： TODO
 */
@Controller
@ResponseBody
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;

    @PostMapping("/typeList")
    public Result getCustomerTypeLikeList(@RequestBody Map<String,Object> param){
        String type = (String) param.get("customerType");
        if (type == null) {
            type = "";
        }
        return Result.success(customerService.getCustomerTypeLikeList(type));
    }

    @PostMapping("/likeList")
    public Result<List<Customer>> getCustomerLikeList(@RequestBody Map<String,Object> param){
        String customerName = (String) param.get("customerName");
        return Result.success(customerService.getCustomerLikeList(customerName));
    }

    @PostMapping("/type")
    public Result getCustomerType(@RequestBody Map<String,Object> param){
        try{
            Long itemId = Long.parseLong((String) param.get("itemId"));
            Long customerId = Long.parseLong((String) param.get("customerId"));
            String type = customerTypeService.getCustomerTypeByRule(customerId,itemId);
            if(StringUtils.isEmpty(type)){
                return Result.fail().message("无匹配类型，请飞书联系管理员！");
            }
            return Result.success(customerTypeService.getCustomerTypeByRule(customerId,itemId),null);
        }catch (Exception e) {
            return Result.fail("输入格式有误！",null);
        }
    }

}
