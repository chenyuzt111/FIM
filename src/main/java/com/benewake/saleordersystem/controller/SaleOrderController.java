package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.annotation.SalesmanRequired;
import com.benewake.saleordersystem.annotation.TrackingTime;
import com.benewake.saleordersystem.entity.*;
import com.benewake.saleordersystem.entity.VO.FilterCriteria;
import com.benewake.saleordersystem.entity.VO.FilterVo;
import com.benewake.saleordersystem.entity.VO.StartInquiryVo;
import com.benewake.saleordersystem.service.*;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.HostHolder;
import com.benewake.saleordersystem.utils.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author Lcs
 * @since 2023年07月05 16:18
 * 描 述： TODO
 */
@Controller
@RequestMapping("/order")
@ResponseBody
@Slf4j
public class SaleOrderController implements BenewakeConstants {

    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private ViewColService viewColService;
    @Autowired
    private ViewService viewService;

    @GetMapping("/stateList")
    public Result getStateList(){
        List<String> stateList = inquiryService.getStateList();
        return Result.success(stateList);
    }
    @PostMapping("/inquiryTypeList")
    public Result getInquiryTypeList(@RequestBody Map<String,Object> param){
        String key = (String) param.get("inquiryType");
        if(key==null) key = "";
        return Result.success(inquiryService.getInquiryTypeList(key));
    }
    @PostMapping("/inquiryCodeList")
    public Result getInquiryLikeList(@RequestBody Map<String,Object> param){
        String key = (String) param.get("inquiryCode");
        if(key==null) key = "";
        List<Inquiry> res = inquiryService.getInquiryCodeLikeList(key);
        return Result.success(res);
    }

    /**
     * 已登录用户根据tableid获取对应的新增视图，若无新增视图则为空
     * @return
     */
    @PostMapping("/views")
    @LoginRequired
    public Result<List<View>> getViewByTableId(@RequestBody Map<String,Object> param){
        Long tableId = Long.parseLong((String) param.get("tableId"));
        User u = hostHolder.getUser();
        List<View> lists = viewService.getUserView(u.getId(),tableId);

        return Result.success(lists);
    }
    @PostMapping("/cols")
    public Result<Map<String,Object>> getAllCols(@RequestBody Map<String,Object> param){
        Long tableId = Long.parseLong((String) param.get("tableId"));
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> maps = viewService.getAllCols(tableId);
        map.put("cols",maps);
        return Result.success(map);
    }

    /**
     * 1-6订单列表过滤查询
     * viewId = -1 表示查看我的  viewId = 0 表示查看全部
     * @param filterVo
     * @return
     */
    @PostMapping("/Lists")
    @LoginRequired
    @TrackingTime
    public Result<Map<String,Object>> selectList(@RequestBody FilterVo filterVo){

        Map<String,Object> res = new HashMap<>();
        if(filterVo==null || filterVo.getTableId()==null || filterVo.getViewId()==null){
            return Result.fail("未选择表或视图！",null);
        }
        // 当前登录用户
        User loginUser = hostHolder.getUser();
        if(filterVo.getViewId() <= 0){
            // 我的视图
            // 列信息
            List<Map<String,Object>> cols = viewColService.getCols(filterVo.getTableId(), filterVo.getViewId(), loginUser.getUserType().equals(1L));
            // 查看我的
            List<Map<String,Object>> lists;
            if(loginUser.getUserType().equals(1L) || (filterVo.getTableId().equals(1L)&&filterVo.getViewId().equals(-1L))){
                // 管理员 或系统全部
                lists = inquiryService.selectSalesOrderVoList(filterVo.getFilterCriterias(),null);
            }else{
                // 普通用户
                lists = inquiryService.selectSalesOrderVoList(filterVo.getFilterCriterias(),loginUser.getUsername());
            }
            res.put("lists",lists);
            res.put("cols",cols);
        }else{
            // 个人设定的视图
            // 列信息
            List<Map<String,Object>> cols = viewColService.getCols(filterVo.getTableId(),filterVo.getViewId(),loginUser.getUserType().equals(1L));
            List<FilterCriteria> filters = filterVo.getFilterCriterias()==null?new ArrayList<>():filterVo.getFilterCriterias();
            // 添加方案默认筛选信息
            for(Map<String,Object> col : cols){
                String colValue = (String) col.get("col_value");
                if(!StringUtils.isEmpty(colValue)){
                    filters.add(new FilterCriteria((String) col.get("col_name_ENG"),
                            StringUtils.isEmpty(col.get("value_operator"))?EQUAL: (String) col.get("value_operator"),colValue));
                }
            }
            // 根据筛选条件获取信息
            List<Map<String,Object>> lists = inquiryService.selectSalesOrderVoList(filters, loginUser.getUsername());

            res.put("lists",lists);
            res.put("cols",cols);
        }
        return Result.success(res);
    }

    @PostMapping("/saveView")
    @LoginRequired
    @Transactional
    public Result<String> savePlan(@RequestBody FilterVo filterVo){
        if(filterVo.getTableId() == null){
            return Result.fail("表id不能为空！",null);
        }
        if(CollectionUtils.isEmpty(filterVo.getCols())){
            return Result.fail("新增方案的列信息不能为空！",null);
        }
        // 获取当前用户
        User u = hostHolder.getUser();
        if(filterVo.getViewId()==null && viewService.isExist(filterVo.getTableId(),u.getId(),filterVo.getViewName())){
            return Result.fail("该视图名称为空或已存在!",null);
        }
        // 持久化视图
        View view = new View();
        view.setTableId(filterVo.getTableId());
        view.setViewName(filterVo.getViewName());
        view.setUserId(u.getId());
        if(filterVo.getViewId()==null){
            viewService.saveView(view);
        }else{
            view.setViewId(filterVo.getViewId());
            viewService.updateView(view);
        }

        List<ViewCol> cols = filterVo.getCols();
        for(ViewCol vc : cols){
            vc.setViewId(view.getViewId());
        }
        // 删掉原来的列信息
        viewColService.deleteCols(view.getViewId());
        // 保存新增列信息
        viewColService.saveCols(cols);
        if(filterVo.getViewId()==null) {
            return Result.success("方案添加成功！",null);
        } else {
            return Result.success("方案修改成功！",null);
        }
    }


    @PostMapping("/update")
    @SalesmanRequired
    @Transactional
    public Result updateInquired(@RequestBody Inquiry inquiry){
        if(inquiry == null){
            return Result.fail().message("请添加选择要修改的订单！");
        }
        if(inquiry.getInquiryType()==null){
            return Result.fail().message("请选择订单类型");
        }
        if(inquiry.getState()==null || inquiry.getState()==-1){
            return Result.fail().message("订单无效！");
        }
        // 订单参数有效判断
        Map<String,Object> res = inquiryService.addValid(inquiry);
        if(res.size()>0){
            return Result.fail((String) res.get("error"),null);
        }
        // 原订单设置无效
        inquiryService.updateState(inquiry.getInquiryId(),-1);
        // 新增修改后的订单
        inquiry.setInquiryId(null);
        inquiry.setCreatedUser(hostHolder.getUser().getId());
        inquiryService.save(inquiry);
        return Result.success("修改成功！",inquiry.getInquiryId());
    }
    /**
     * 新增询单信息 及 开始询单 （只能新增或询单）
     * startInquiry = 1 表示询单
     * @return
     */
    @PostMapping("/save")
    @SalesmanRequired
    @Transactional
    public Result addInquiries(@RequestBody StartInquiryVo param){
        List<Inquiry> newInquiries = param.getInquiryList();
        Integer startInquiry = param.getStartInquiry();

        Map<String,Object> map = new HashMap<>();
        if(newInquiries == null){
            return Result.fail("请添加至少一条询单信息",null);
        }
        // 保存 或 单据id不存在
        if(startInquiry == 0 || newInquiries.get(0).getInquiryId()==null){
            User user = hostHolder.getUser();
            Date nowTime = new Date();
            if(newInquiries.get(0).getInquiryType()==null){
                return Result.fail("请选择订单类型",null);
            }
//            if(newInquiries.get(0).getInquiryCode()!=null && !inquiryService.containsCode(newInquiries.get(0).getInquiryCode())){
//                return Result.fail("单据编号不存在！",null);
//            }
            // 获取订单编码列表
            List<String> inquiryCodes = new ArrayList<>();
            // 逐条分析询单是否合法
            for(Inquiry inq : newInquiries){
                Map<String,Object> res = inquiryService.addValid(inq);
                if(res.size()>0){
                    return Result.fail((String) res.get("error"),null);
                }
                // 设置创建人信息以及单据编号
                inq.setCreatedUser(user.getId());
                if(inq.getInquiryCode()==null){
                    inq.setInquiryCode(inquiryService.getDocumentNumberFormat(inq.getInquiryType(),1).get(0));
                }
                inquiryCodes.add(inq.getInquiryCode());
                inq.setState(0);
            }
            map.put("inquiryCode",inquiryCodes);
            // 添加运输信息
            deliveryService.insertLists(newInquiries);
            // 全部通过加入数据库
            inquiryService.insertLists(newInquiries);
            List<Long> ids = new ArrayList<>();
            for(Inquiry inq:newInquiries){
                ids.add(inq.getInquiryId());
            }
            map.put("ids",ids);
        }
        // 询单
        if(startInquiry!=null && startInquiry != 0){
            List<Inquiry> fail = new ArrayList<>();
            List<Inquiry> success = new ArrayList<>();
            int ind = 1;
            try {
                for(int i=0;i<newInquiries.size();++i,++ind){
                    // 根据订单
                    Inquiry inquiry = inquiryService.getInquiryById(newInquiries.get(i).getInquiryId());
                    Item item = itemService.findItemById(inquiry.getItemId());
                    if(item.getItemType() == ITEM_TYPE_MATERIALS_AND_SOFTWARE_BESPOKE ||
                            item.getItemType() == ITEM_TYPE_RAW_MATERIALS_BESPOKE ||
                            item.getQuantitative()==0 || inquiry.getSaleNum()>item.getQuantitative()){
                        // 询单失败
                        // 物料类型为 新增原材料+软件定制 或 新增原材料定制 或 物料标准数量为0 或 当前数量大于物料标准数量
                        fail.add(inquiry);
                    }else{
                        success.add(inquiry);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
                throw new RuntimeException("参数有误！");
            }
            //
            List<String> resStr = new ArrayList<>();
            fail.forEach(f->{
                resStr.add("单据编号:"+f.getInquiryCode()+"，请飞书联系管理员!");
            });
            if(success.size() > 0){
                //map.put("success",success.size()+"个订单开始询单！");

                // 询单功能（待添加)   异步或消息队列

                // 设置state+1 （之后移到异步操作中或使用消息队列）
                success.forEach(s->s.setState(s.getState()+1));
                // 更新数据库  （之后移到异步操作中或使用消息队列）
                inquiryService.updateByInquiry(success);
                //return Result.success("已开始询单！",map);
                resStr.add("APS暂未上线，今日内计划手动反馈日期！");
            }
            return Result.success(String.join("\r\n",resStr),map);
        }
        return Result.success("保存成功！",map);
    }

    /**
     * 删除接口 销售员只能删除销售员id或创建人id等于自己id的数据
     * @param param
     * @return
     */
    @PostMapping("/delete")
    @SalesmanRequired
    public Result deleteOrder(@RequestBody Map<String,Long> param){
        Long orderId = param.get("orderId");
        Inquiry inquiry = inquiryService.getInquiryById(orderId);
        User u = hostHolder.getUser();
        if( u.getUserType().equals(USER_TYPE_SALESMAN)&&
                (inquiry.getSalesmanId().equals(u.getId())||inquiry.getCreatedUser().equals(u.getId())) ||
                u.getUserType().equals(USER_TYPE_ADMIN) || u.getUserType().equals(USER_TYPE_SYSTEM)
        ){
            boolean res = inquiryService.deleteOrder(orderId);
            if(!res){
                return Result.fail("订单不存在！",null);
            }else {
                return Result.success("删除成功！",null);
            }
        }else{
            return Result.fail().message("用户权限不够！");
        }
    }

    @PostMapping("/importExcel")
    @SalesmanRequired
    public Result addOrdersByExcel(@RequestParam("file")MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        if(file.isEmpty()){
            return Result.fail("文件为空！",null);
        }
        val split = file.getOriginalFilename().split("\\.");
        if(!"xlsx".equals(split[1]) && !"xls".equals(split[1])){
            return Result.fail().message("请提供.xlsx或.xls为后缀的Excel文件");
        }
        try {
            map = inquiryService.saveDataByExcel(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(map.containsKey("error")){
            return Result.fail().message((String) map.get("error"));
        }else{
            return Result.success().message((String) map.get("success"));
        }
    }

    @PostMapping("/deleteView")
    public Result deleteView(@RequestBody View view){
        if(view.getViewId()==null) {
            return Result.fail("viewId不能为空！",null);
        }
        boolean isSuccess = viewService.deleteView(view.getViewId());
        return isSuccess?Result.success("删除成功!",null) : Result.fail("删除失败或视图不存在！",null);
    }
}
