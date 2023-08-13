package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.service.DeliveryService;
import com.benewake.saleordersystem.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月15 11:34
 * 描 述： TODO
 */
@Api(tags = "运输信息接口")
@Controller
@RequestMapping("/delivery")
@ResponseBody
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     * 更新当前未签收订单的最新状态
     * @return
     */
    @ApiOperation("更新用户订单物流信息")
    @GetMapping("/update")
    @LoginRequired
    public Result<String> updateDelivery(){
        boolean flag = deliveryService.updateDelivery();
        if(flag){
            return Result.success("运输状态更新成功！",null);
        }
        return Result.fail("运输状态更新失败，请重试！",null);
    }

    @PostMapping("/deliveryCodeList")
    @LoginRequired
    public Result getDeliveryCodeList(@RequestBody Map<String,Object> param){
        String deliveryCode = (String) param.get("deliveryCode");
        if(deliveryCode == null) deliveryCode = "";
        return Result.success(deliveryService.getDeliveryCodeList(deliveryCode));
    }

    @PostMapping("/deliveryStateList")
    public Result getDeliveryStateList(@RequestBody Map<String,Object> param){
        String deliveryState = (String) param.get("deliveryState");
        if(deliveryState==null) deliveryState = "";
        return Result.success(deliveryService.getDeliveryStateList(deliveryState));
    }

}
