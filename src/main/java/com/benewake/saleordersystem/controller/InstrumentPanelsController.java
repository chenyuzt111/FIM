package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.AdminRequired;
import com.benewake.saleordersystem.service.PastOrderService;
import com.benewake.saleordersystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月15 15:54
 * 描 述： TODO
 */
@Controller
@ResponseBody
@RequestMapping("/instrument")
public class InstrumentPanelsController {

    @Autowired
    private PastOrderService pastOrderService;

    /**
     * 更新历史订单数据  isAll -true 重新导入全部历史订单 -false 根据上次导入的节点进行追加
     * @return
     */
    @PostMapping("/update")
    @AdminRequired
    public Result<String> getTest(@RequestBody Map<String,Integer> param){
        Integer isAll = param.get("isAll");
        if(isAll >= 1){
            pastOrderService.savePastOrder(true);
        }else if(isAll == 0){
            pastOrderService.savePastOrder(false);
        }else{
            return Result.fail("参数错误！",null);
        }
        return Result.success("历史数据更新成功！",null);
    }

}
