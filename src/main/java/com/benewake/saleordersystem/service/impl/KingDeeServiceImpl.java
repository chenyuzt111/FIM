package com.benewake.saleordersystem.service.impl;

import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.Transfer.*;
import com.benewake.saleordersystem.entity.Past.SaleOut;
import com.benewake.saleordersystem.entity.Past.Withdraw;
import com.benewake.saleordersystem.service.KingDeeService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.CommonUtils;
import com.kingdee.bos.webapi.entity.QueryParam;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月04 11:23
 * 描 述： TODO
 */
@Service
public class KingDeeServiceImpl implements KingDeeService, BenewakeConstants {

    @Autowired
    private K3CloudApi api;
    @Override
    public <T> List<T> searchData(String formId, List<String> fieldKeys, String queryFilters,int limit,Class type) throws Exception {
        List<T> res = new ArrayList<>();
        int offset = 0;
        do{
            int lim = Math.min(10000,limit);
            int size = res.size();
            QueryParam queryParam = new QueryParam();
            queryParam.setFormId(formId)
                    .setFieldKeys(String.join(",",fieldKeys))
                    .setFilterString(queryFilters)
                    .setLimit(lim)
                    .setStartRow(offset);
            res.addAll(api.executeBillQuery(queryParam, type));
            if(res.size() == size) break;
            offset += lim;
            limit -= 10000;
        }while (limit>0);
        return res;
    }

    @Override
    public Map<String, String> getIdToNameList(int choose) throws Exception {
        if(choose == 1) {
            return getMaterialIdToNameList();
        } else if(choose == 2) {
            return getSalesmanIdToNameList();
        } else if(choose == 3) {
            return getUserIdToNameList();
        } else if(choose == 4) {
            return getCustomerIdToName();
        }else if(choose == 5){
            return getOrgIdToName();
        }

        return null;
    }

    /**
     * 物料映射表
     * @return
     * @throws Exception
     */
    private Map<String,String> getMaterialIdToNameList() throws Exception {
        QueryParam queryParam = new QueryParam();
        queryParam.setFormId("BD_MATERIAL");
        queryParam.setFieldKeys("FMaterialId,FNumber");
        List<MaterialIdToName> midToName = api.executeBillQuery(queryParam, MaterialIdToName.class);
        Map<String,String> mtn = new HashMap<>();
        midToName.forEach(c->{
            mtn.put(c.getFMaterialId(),c.getFNumber());
        });
        return mtn;
    }

    /**
     * 创建人或审核人映射表
     * @return
     * @throws Exception
     */
    private Map<String,String> getUserIdToNameList() throws Exception {
        QueryParam queryParam = new QueryParam();
        queryParam.setFormId("SEC_User");
        queryParam.setFieldKeys("FUserID,FName");
        List<CreateIdToName> crtidToName = api.executeBillQuery(queryParam, CreateIdToName.class);
        Map<String,String> critn = new HashMap<>();
        crtidToName.forEach(c->{
            critn.put(c.getFUserID(),c.getFName());
        });
        return critn;
    }

    /**
     * 销售员映射表
     * @return
     */
    private Map<String,String> getSalesmanIdToNameList() throws Exception {
        QueryParam queryParam = new QueryParam();
        queryParam.setFormId("BD_Saler");
        queryParam.setFieldKeys("fid,FName");
        List<SalerIdToName> sidToName = api.executeBillQuery(queryParam, SalerIdToName.class);
        Map<String,String> sitn = new HashMap<>();
        sidToName.forEach(c->{
            sitn.put(c.getFid(), c.getFName());
        });
        return sitn;
    }

    /**
     * 客户映射表
     * @return
     */
    private Map<String,String> getCustomerIdToName() throws Exception {
        QueryParam queryParam = new QueryParam();
        queryParam.setFormId("BD_Customer");
        queryParam.setFieldKeys("FCustId,FName");
        List<CustIdToName> cidToName = api.executeBillQuery(queryParam, CustIdToName.class);
        Map<String,String> citn = new HashMap<>();
        cidToName.forEach(c -> {
            citn.put(c.getFCustId(),c.getFName());
        });
        return citn;
    }

    /**
     * 仓库组织映射表
     * @return
     * @throws Exception
     */
    private Map<String,String> getOrgIdToName() throws Exception {
        QueryParam queryParam = new QueryParam();
        queryParam.setFormId("ORG_Organizations");
        queryParam.setFieldKeys("FOrgID,FName");
        List<OrgIdToName> cidToName = api.executeBillQuery(queryParam, OrgIdToName.class);
        Map<String,String> citn = new HashMap<>();
        cidToName.forEach(c -> {
            citn.put(c.getFOrgID(),c.getFName());
        });
        return citn;
    }

    public List<SaleOut> searchSaleOutList1(int limit, String time) throws Exception {
        List<String> queryFilters = new ArrayList<>();
        // 发货组织为北醒(北京)光子科技有限公司
        queryFilters.add("FStockOrgId = 1");
        // 客户不为北醒（北京）商贸有限公司
        queryFilters.add("FCustomerID != 104169");
        // 客户不为北京北醒智能设备有限公司
        queryFilters.add("FCustomerID != 458103");
        queryFilters.add(String.format("FDate >= '%s'",time));
        // 临时 可删
        //queryFilters.add("FDate >= '2023-6-1'");
        //queryFilters.add(String.format("FNOTE = '%s'","XSYG202405070050"));
        return searchSaleOutList(String.join(" and ",queryFilters),limit);
    }

    @Override
    public List<SaleOut> searchSaleOutList2(int limit, String time) throws Exception {
        List<String> queryFilters = new ArrayList<>();
        // 发货组织为北醒（北京）商贸有限公司
        queryFilters.add("FStockOrgId = 100884");
        queryFilters.add(String.format("FDate >= '%s'",time));
        //queryFilters.add("FDate >= '2023-06-20'");
        return searchSaleOutList(String.join(" and ",queryFilters),limit);
    }

    @Override
    public List<SaleOut> searchSaleOutList(String queryFilters, int limit) throws Exception {
        String formId = "SAL_OUTSTOCK";
        List<String> fieldKeys = new ArrayList<>();
        //fieldKeys.add("FBillNo");
        fieldKeys.add("FMaterialID");
        fieldKeys.add("FMaterialName");
        fieldKeys.add("FRealQty");
        //fieldKeys.add("FStockOrgId");
        fieldKeys.add("FCustomerID");
        fieldKeys.add("FSalesManID");
        fieldKeys.add("FDate");
        fieldKeys.add("FSoorDerno");
        // 临时获取 运输单号和收件人电话号码  FIM表示fim单据编号用于唯一匹配
//        fieldKeys.add("FCarriageNO");
//        fieldKeys.add("F_ora_Text2");
//        fieldKeys.add("FIM");
        List<SaleOut> lists = searchData(formId,fieldKeys,queryFilters,limit, SaleOut.class);

        // 替换信息
        Map<String,String> mtn = getIdToNameList(1);
        Map<String,String> ctn = getIdToNameList(4);
        Map<String,String> stn = getIdToNameList(2);
        //Map<String,String> otn = getIdToNameList(5);

        lists.forEach(c->{
            try {
                c.setFDate(CommonUtils.KingDeeDateFormat(c.getFDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            c.setFMaterialID(mtn.get(c.getFMaterialID()));
            c.setFCustomerID(ctn.get(c.getFCustomerID()));
            c.setFSalesManID(stn.get(c.getFSalesManID()));
            //c.setFStockOrgId(otn.get(c.getFStockOrgId()));
        });
        return lists;
    }

    @Override
    public List<Withdraw> searchWithdrawList(String queryFilters, int limit) throws Exception {
        String formId = "SAL_RETURNSTOCK";
        List<String> fieldKeys = new ArrayList<>();
        fieldKeys.add("FMaterialId");
        fieldKeys.add("FMaterialName");
        fieldKeys.add("FRealQty");
        //fieldKeys.add("FSaleOrgId");
        fieldKeys.add("FRetcustId");
        fieldKeys.add("FSalesManId");
        fieldKeys.add("FDate");
        fieldKeys.add("FOrderNo");
        List<Withdraw> lists = searchData(formId,fieldKeys,queryFilters,limit, Withdraw.class);

        Map<String,String> mtn = getIdToNameList(1);
        Map<String,String> ctn = getIdToNameList(4);
        Map<String,String> stn = getIdToNameList(2);
        //Map<String,String> otn = getIdToNameList(5);

        lists.forEach(c->{
            try {
                c.setFDate(CommonUtils.KingDeeDateFormat(c.getFDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            c.setFMaterialId(mtn.get(c.getFMaterialId()));
            c.setFRetcustId(ctn.get(c.getFRetcustId()));
            c.setFSalesManId(stn.get(c.getFSalesManId()));
            //c.setFSaleOrgId(otn.get(c.getFSaleOrgId()));
        });
        return lists;
    }

    @Override
    public List<Withdraw> searcWithdrawList1(int limit, String time) throws Exception {
        List<String> queryFilters = new ArrayList<>();
        // 发货组织为北醒(北京)光子科技有限公司
        queryFilters.add("FSaleOrgId = 1");
        // 客户不为北醒（北京）商贸有限公司
        queryFilters.add("FRetcustId != 104169");
        // 客户不为北京北醒智能设备有限公司
        queryFilters.add("FRetcustId != 458103");
        queryFilters.add(String.format("FDate >= '%s'",time));
        return searchWithdrawList(String.join(" and ",queryFilters),limit);
    }

    @Override
    public List<Withdraw> searcWithdrawList2(int limit, String time) throws Exception {
        List<String> queryFilters = new ArrayList<>();
        // 发货组织为北醒（北京）商贸有限公司
        queryFilters.add("FSaleOrgId = 100884");
//        queryFilters.add("FDate >= '2023-06-20'");
        queryFilters.add(String.format("FDate >= '%s'",time));
        return searchWithdrawList(String.join(" and ",queryFilters),limit);
    }

    @Override
    public List<SaleOut> selectFCarriageNO(List<Delivery> deliveries) throws Exception {
        if(deliveries == null || deliveries.size() == 0) return new ArrayList<>();
        String formId = "SAL_OUTSTOCK";
        List<String> fieldKeys = new ArrayList<>();
        fieldKeys.add("FIM,FCarriageNO,F_ora_Text2");
        List<String> queryFields = new ArrayList<>();
        deliveries.forEach(d->queryFields.add(String.format("FIM = '%s'",d.getInquiryCode())));
        List<SaleOut> lists = searchData(formId,fieldKeys,String.join(" or ",queryFields),Integer.MAX_VALUE, SaleOut.class);
        return lists;
    }
}
