package cn.hdbase.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2016/12/13.
 */
@Data
@Component("tokenConfig")
@ConfigurationProperties(locations="classpath:token.properties")
public class TokenConfig {

    String redisHost;

    Integer redisPort;

    String redisPassword;

    String redisTokenName;

    public static TokenConfig getInstance() {
        return (TokenConfig) SpringApplicationContextHolder.getSpringBean("tokenConfig");
    }

}
