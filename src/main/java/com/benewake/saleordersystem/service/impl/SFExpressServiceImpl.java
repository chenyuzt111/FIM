package com.benewake.saleordersystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.benewake.saleordersystem.entity.Delivery;
import com.benewake.saleordersystem.entity.sfexpress.Route;
import com.benewake.saleordersystem.entity.sfexpress.SF_SEARCH_RESULT;
import com.benewake.saleordersystem.service.KingDeeService;
import com.benewake.saleordersystem.service.SFExpressService;
import com.benewake.saleordersystem.utils.api.SFUtils;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.HttpClientUtil;
import com.sf.csim.express.service.IServiceCodeStandard;
import com.sf.csim.express.service.code.ExpressServiceCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: xsong
 * date:2022/11/8 15:35
 * 描述：
 **/
@Service
public class SFExpressServiceImpl implements SFExpressService {

    @Autowired
    private KingDeeService kingDeeService;

    //顾客编码
    private static final String CLIENT_CODE = "LCSZ82CQOO";
    // 生产环境校验码
    private static final String CHECK_WORD = "x6x14lUFjJqDCfb1mJ1H2Lp33R1ajCZ1";
    //生产的地址
    private static final String CALL_URL_PRO = "https://bspgw.sf-express.com/std/service";


    @Override
    public SF_SEARCH_RESULT findRoutesByCode(String code, String tel) {
        try {
            //查路由
            IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_ROUTES;

            Map<String, String> params = new HashMap<>();

            String timeStamp = String.valueOf(System.currentTimeMillis());

            String msgData = "{\"trackingType\": \"1\",\"trackingNumber\": [\"" + code + "\"],\"checkPhoneNo\": \"" + tel + "\"}";
            //System.out.println(msgData);
            // 顾客编码
            params.put("partnerID", CLIENT_CODE);
            params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
            // 接口服务码
            params.put("serviceCode", standardService.getCode());
            params.put("timestamp", timeStamp);
            params.put("msgData", msgData);
            params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));
            String result = HttpClientUtil.post(CALL_URL_PRO, params);
            //System.out.println(result);
            SF_SEARCH_RESULT routes = JSON.parseObject(result, SF_SEARCH_RESULT.class);
            return routes;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    public SF_SEARCH_RESULT searchPromitm(String code, String tel) {
        try {
            //预计派送时间
            IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_PROMITM;
            CallExpressServiceTools tools = CallExpressServiceTools.getInstance();

            // set common header
            Map<String, String> params = new HashMap<>();
            String timeStamp = String.valueOf(System.currentTimeMillis());
            String msgData = "{\n" +
                    "    \"searchNo\": \"" + code + "\",\n" +
                    "    \"checkType\": 1,\n" +
                    "    \"checkNos\": [\"" + tel + "\"]\n" +
                    "}";
            // 顾客编码
            params.put("partnerID", CLIENT_CODE);
            params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
            // 接口服务码
            params.put("serviceCode", standardService.getCode());
            params.put("timestamp", timeStamp);
            params.put("msgData", msgData);
            params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));
            String result = HttpClientUtil.post(CALL_URL_PRO, params);
            SF_SEARCH_RESULT routes = JSON.parseObject(result, SF_SEARCH_RESULT.class);
            return routes;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    public Route getLastestRouteByFCarriageNO(Delivery delivery){
        //SaleOut saleOut = kingDeeService.selectFCarriageNO(fCarriageNO);
        if(delivery != null){
            if(StringUtils.isBlank(delivery.getDeliveryPhone())) {
                return null;
            } else{
                return SFUtils.getLastestRemark(findRoutesByCode(delivery.getDeliveryCode(),delivery.getDeliveryPhone()));
            }
        }else{
            return null;
        }
    }
}
