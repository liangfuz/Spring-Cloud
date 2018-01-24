package cn.hdbase.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2016/12/13.
 */
@Data
@Component("alipayConfig")
@ConfigurationProperties(locations="classpath:alipay.properties")
public class AlipayConfig {

    String appId;

    String sellerId;

    String appPrivateKey;

    String appPublicKey;

    String alipayPublicKey;

    String apiUrl;

    String authUrl;

    public static AlipayConfig getInstance(){
        return (AlipayConfig) SpringApplicationContextHolder.getSpringBean("alipayConfig");
    }
}
