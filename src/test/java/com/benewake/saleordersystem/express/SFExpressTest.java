package com.benewake.saleordersystem.express;

import com.alibaba.fastjson2.JSONObject;
import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.HttpClientUtil;
import com.sf.csim.express.service.IServiceCodeStandard;
import com.sf.csim.express.service.code.ExpressServiceCodeEnum;
import net.minidev.json.JSONUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * author: xsong
 * date:2022/11/7 11:04
 * 描述：顺丰速运测试类
 **/
public class SFExpressTest {
    private static final String CLIENT_CODE = "LXS4I2hu6k";  //此处替换为您在丰桥平台获取的顾客编码
    private static final String CHECK_WORD2 = "42cJbEKFaXnIoPY9i1GibjHUk8ovRm8i";//此处替换为您在丰桥平台获取的校验码
    private static final String CHECK_WORD = "xrVsEJZ98Ic0kRGEJKE479ZnqiRtntji";//此处替换为您在丰桥平台获取的校验码

    //沙箱环境的地址
    private static final String CALL_URL_BOX = "https://sfapi-sbox.sf-express.com/std/service";
    //生产环境的地址
    private static final String CALL_URL_PROD = "https://sfapi.sf-express.com/std/service";

    public static void main(String[] args) throws UnsupportedEncodingException {
        /**ExpressServiceCodeEnum     对应速运类-快递APIs
         POSTServiceCodeEnum        对应速运类-驿站APIs
         YJTServiceCodeEnum         对应解决方案-医寄通APIs
         EPSServiceCodeEnum         对应解决方案-快递管家APIs
         详情见code目录下枚举类，客户可自行修改引用的该类
         **/
//        IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_CREATE_ORDER; //下订单
//        	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_ORDER_RESP; //查订单
        //  IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_UPDATE_ORDER;//订单取消
        // 	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_FILTER_ORDER_BSP;//订单筛选
        IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_ROUTES;//查路由
        //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_GET_SUB_MAILNO;//子单号
        //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_QUERY_SFWAYBILL;//查运费
        //	IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_REGISTER_ROUTE;//注册路由
//        IServiceCodeStandard standardService = ExpressServiceCodeEnum.EXP_RECE_SEARCH_PROMITM;//预计派送时间

        CallExpressServiceTools tools = CallExpressServiceTools.getInstance();

        // set common header
        Map<String, String> params = new HashMap<>();

        String timeStamp = String.valueOf(System.currentTimeMillis());
        String msgData = "{\"trackingType\": \"1\",\"trackingNumber\": \"SF1688857087748\",\"checkPhoneNo\": \"17773548562\"}";
//        String msgData = "{\n" +
//                "    \"searchNo\": \"SF1372994622208\",\n" +
//                "    \"checkType\": 1,\n" +
//                "    \"checkNos\": [\"18845109315\"]\n" +
//                "}";
        params.put("partnerID", CLIENT_CODE);  // 顾客编码
        params.put("requestID", UUID.randomUUID().toString().replace("-", ""));
        params.put("serviceCode", standardService.getCode());// 接口服务码
        params.put("timestamp", timeStamp);
        params.put("msgData", msgData);
        params.put("msgDigest", CallExpressServiceTools.getMsgDigest(msgData, timeStamp, CHECK_WORD));

        String result = HttpClientUtil.post(CALL_URL_BOX, params);
        JSONObject map = JSONObject.parseObject(result);
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
        System.out.println("===调用地址 ===" + CALL_URL_BOX);
        System.out.println("===顾客编码 ===" + CLIENT_CODE);
        System.out.println("===返回结果：" + result);

    }

}
