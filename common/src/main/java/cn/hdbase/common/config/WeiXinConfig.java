package cn.hdbase.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2016/12/13.
 */
@Data
@Component("weiXinConfig")
@ConfigurationProperties(locations="classpath:weixin.properties")
public class WeiXinConfig {

    String appId;

    String appSecret;

    String mchId;

    String key;

    String mchKeyStorePath;

    String newsDishOrderInfo;
    String newsGetFoodInfo;
    String newsRefundApplyInfo;
    String newsRefundSuccessInfo;
    String newsRefundFailInfo;
    String newsCouponExpireInfo;

    public static WeiXinConfig getInstance(){
        return (WeiXinConfig) SpringApplicationContextHolder.getSpringBean("weiXinConfig");
    }
}
