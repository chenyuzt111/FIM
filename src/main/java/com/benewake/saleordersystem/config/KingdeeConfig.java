package com.benewake.saleordersystem.config;

import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.kingdee.bos.webapi.entity.IdentifyInfo;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lcs
 * 金蝶bean配置
 * @since 2023年6月27日 17:26
 */
@Configuration
public class KingdeeConfig implements BenewakeConstants {

    @Bean
    public K3CloudApi k3CloudApi(){
        IdentifyInfo iden = new IdentifyInfo();
        iden.setUserName(X_KDAPI_USERNAME);
        iden.setAppId(X_KDAPI_APPID);
        iden.setdCID(X_KDAPI_ACCTID);
        iden.setAppSecret(X_KDAPI_APPSEC);
        iden.setlCID(X_KDAPI_LCID);
        iden.setServerUrl(X_KDAPI_SERVICEURL);
        iden.setOrgNum("100");
        K3CloudApi api = new K3CloudApi(iden);
        return api;
    }
}
