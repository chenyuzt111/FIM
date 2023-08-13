package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.entity.Item;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.entity.VO.ItemVo;
import com.benewake.saleordersystem.service.ItemService;
import com.benewake.saleordersystem.utils.HostHolder;
import com.benewake.saleordersystem.utils.Result;
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
 * @since 2023年07月12 11:21
 * 描 述： TODO
 */
@Controller
@RequestMapping("/item")
@ResponseBody
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/itemTypeList")
    public Result getItemTypeList(@RequestBody Map<String,Object> param){
        String itemType = (String) param.get("itemType");
        if(itemType==null) itemType = "";
        return Result.success(itemService.getItemTypeList(itemType));
    }

    @PostMapping("/likeList")
    public Result getlikeItemList(@RequestBody Map<String,Object> param){
        String itemCode = (String) param.get("itemCode");
        if(itemCode==null) itemCode = "";
        return Result.success(itemService.itemCodeLikeList(itemCode));
    }
    
    @PostMapping("/itemNameList")
    public Result getItemNameList(@RequestBody Map<String,Object> param){
        String key = (String) param.get("itemName");
        if(key==null) key = "";
        return Result.success(itemService.getItemNameList(key));
    }

    @PostMapping("/follow")
    @LoginRequired
    public Result setTop(@RequestBody ItemVo itemVo){
        User u = hostHolder.getUser();



        return Result.success().message("置顶成功！");
    }


}
