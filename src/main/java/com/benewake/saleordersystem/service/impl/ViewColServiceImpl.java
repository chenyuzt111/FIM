package com.benewake.saleordersystem.service.impl;

import com.benewake.saleordersystem.entity.ViewCol;
import com.benewake.saleordersystem.mapper.ViewColMapper;
import com.benewake.saleordersystem.service.ViewColService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月12 14:33
 * 描 述： TODO
 */
@Service
public class ViewColServiceImpl implements ViewColService , BenewakeConstants {

    @Autowired
    private ViewColMapper viewColMapper;
    private static final int[] ALL_SALESMAN_MAPS = {1,2,3,4,5,6,7,8,9,10,11,12,13,20};
    private static final int[] ALL_SYSTEM_MAPS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,20};

    private static final int[] INQUIRY_TYPE_SALESMAN_MAPS = {1,2,3,4,5,6,7,8,9,10,11,12,13,20};
    private static final int[] INQUIRY_TYPE_ADMIN_MAPS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,20};
    private static final int[] INQUIRY_TYPE_SALESMAN_T_MAPS = {1,2,3,4,5,6,7,9,10,11,12,13,20};
    private static final int[] INQUIRY_TYPE_ADMIN_T_MAPS = {0,1,2,3,4,5,6,7,9,10,11,12,13,20};

    private static final int[] CUSTOMER_TYPE_SALESMAN_MAPS = {1,2,3,4,5,6,7,8,9,10,11,12,13,20};
    private static final int[] CUSTOMER_TYPE_ADMIN_MAPS = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,20};
    private static final int[] CUSTOMER_TYPE_SALESMAN_T_MAPS = {1,2,3,4,5,6,7,8,9,11,12,13,20};
    private static final int[] CUSTOMER_TYPE_ADMIN_T_MAPS = {0,1,2,3,4,5,6,7,8,9,11,12,13,20};

    private static final int[] ITEM_TYPE_SALESMAN_MAPS = {1,2,3,4,5,6,7,8,9,11,12,13,18,20};
    private static final int[] ITEM_TYPE_ADMIN_MAPS = {0,1,2,3,4,5,6,7,8,9,11,12,13,18,20};
    private static final int[] ITEM_TYPE_SALESMAN_T_MAPS = {1,2,3,4,5,6,7,8,11,12,13,18,20};
    private static final int[] ITEM_TYPE_ADMIN_T_MAPS = {0,1,2,3,4,5,6,7,8,11,12,13,18,20};

    private static final int[] INQUIRY_CHANGE_SALESMAN_MAPS = {1,2,3,4,5,6,7,8,11,12,13,20};
    private static final int[] INQUIRY_CHANGE_ADMIN_MAPS = {0,1,2,3,4,5,6,7,8,11,12,13,20};

    private static final int[] INQUIRY_DELIVERY_SALESMAN_MAPS = {1,4,5,6,7,14,11,12,13,15,16,17,20};
    private static final int[] INQUIRY_DELIVERY_SALESMAN_80_MAPS = {1,4,5,6,7,11,12,15,16,17,20};
    private static final int[] INQUIRY_DELIVERY_SALESMAN_10_MAPS = {1,4,5,6,7,11,12,15,17,20};
    private static final int[] INQUIRY_DELIVERY_SALESMAN_00_MAPS = {1,4,5,6,7,14,11,12,13,15,16,17,20};
    private static final int[] INQUIRY_DELIVERY_ADMIN_MAPS = {0,1,4,5,6,7,14,11,12,13,15,16,17,20};
    private static final int[] INQUIRY_DELIVERY_ADMIN_80_MAPS = {0,1,4,5,6,7,11,12,15,16,17,20};
    private static final int[] INQUIRY_DELIVERY_ADMIN_10_MAPS = {0,1,4,5,6,7,11,12,15,17,20};
    private static final int[] INQUIRY_DELIVERY_ADMIN_00_MAPS = {0,1,4,5,6,7,14,11,12,13,15,16,17,20};
    private static final String[] ENGS = {"salesman_name","inquiry_code","inquiry_init_type","state","item_code","item_name",
        "sale_num","customer_name","inquiry_type","item_type","customer_type","expected_time",
        "arranged_time","delay","order_delivery_progress","delivery_code","receive_time","delivery_state",
                "customize","created_user_name","remark"};
    private static final String[] CNS = {"销售员","单据编号","单据类型","单据状态","物料编码","物料名称","数量","客户名称","订单状态",
                "产品类型","客户类型","期望发货日期","计划反馈日期","是否延期","订单交付进度","运输单号","签收时间","最新状态",
                "是否定制","创建人","备注"};

    private List<Map<String,Object>> getColsTansfer(int[] seq){
        List<Map<String,Object>> maps = new ArrayList<>();
        for(int i=0;i<seq.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("col_name_CN", CNS[seq[i]]);
            map.put("col_name_ENG", ENGS[seq[i]]);
            maps.add(map);
        }
        return maps;
    }


    @Override
    public List<Map<String,Object>> getCols(Long tableId,Long viewId, boolean isAdmin) {
        if(viewId<=0){
            if(tableId.equals(ALL_TABLE)){
                if(viewId==0) return getColsTansfer(ALL_SALESMAN_MAPS);
                else return getColsTansfer(ALL_SYSTEM_MAPS);
            }else if(tableId.equals(INQUIRY_TYPE_TABLE)){
                if(viewId==0){
                    if(isAdmin) return getColsTansfer(INQUIRY_TYPE_ADMIN_MAPS);
                    else return getColsTansfer(INQUIRY_TYPE_SALESMAN_MAPS);
                }else{
                    if(isAdmin) return getColsTansfer(INQUIRY_TYPE_ADMIN_T_MAPS);
                    else return getColsTansfer(INQUIRY_TYPE_SALESMAN_T_MAPS);
                }
            }else if(tableId.equals(CUSTOMER_TYPE_TABLE)){
                if(viewId==0){
                    if(isAdmin) return getColsTansfer(CUSTOMER_TYPE_ADMIN_MAPS);
                    else return getColsTansfer(CUSTOMER_TYPE_SALESMAN_MAPS);
                }else{
                    if(isAdmin) return getColsTansfer(CUSTOMER_TYPE_ADMIN_T_MAPS);
                    else return getColsTansfer(CUSTOMER_TYPE_SALESMAN_T_MAPS);
                }
            }else if(tableId.equals(ITEM_TYPE_TABLE)){
                if(viewId==0){
                    if(isAdmin) return getColsTansfer(ITEM_TYPE_ADMIN_MAPS);
                    else return getColsTansfer(ITEM_TYPE_SALESMAN_MAPS);
                }else{
                    if(isAdmin) return getColsTansfer(ITEM_TYPE_ADMIN_T_MAPS);
                    else return getColsTansfer(ITEM_TYPE_SALESMAN_T_MAPS);
                }
            }else if(tableId.equals(INQUIRY_CHANGE_TABLE)){
                if(isAdmin) return getColsTansfer(INQUIRY_CHANGE_ADMIN_MAPS);
                else return getColsTansfer(INQUIRY_CHANGE_SALESMAN_MAPS);
            }else{
                if(viewId==0){
                    if(isAdmin) return getColsTansfer(INQUIRY_DELIVERY_ADMIN_MAPS);
                    else return getColsTansfer(INQUIRY_DELIVERY_SALESMAN_MAPS);
                }else if(viewId==-1){
                    if(isAdmin) return getColsTansfer(INQUIRY_DELIVERY_ADMIN_80_MAPS);
                    else return getColsTansfer(INQUIRY_DELIVERY_SALESMAN_80_MAPS);
                }else if(viewId==-2){
                    if(isAdmin) return getColsTansfer(INQUIRY_DELIVERY_ADMIN_10_MAPS);
                    else return getColsTansfer(INQUIRY_DELIVERY_SALESMAN_10_MAPS);
                }else if(viewId==-3){
                    if(isAdmin) return getColsTansfer(INQUIRY_DELIVERY_ADMIN_00_MAPS);
                    else return getColsTansfer(INQUIRY_DELIVERY_SALESMAN_00_MAPS);
                }
            }
        }else {
            // 个人方案视图
            return viewColMapper.getColMaps(viewId);
        }
        return null;
    }

    @Override
    public int saveCols(List<ViewCol> cols) {
        return viewColMapper.saveViewColList(cols);
    }

    @Override
    public int deleteCols(Long viewId) {
        return viewColMapper.deleteByViewId(viewId);
    }
}
