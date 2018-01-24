package cn.hdbase.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;


/**
 * Created by Administrator on 2016/12/13.
 */
@Data
@Component("systemConfig")
@ConfigurationProperties(locations="classpath:system.properties")
public class SystemConfig {

    String redisHost;

    String redisPort;

    String redisPassword;

    String accessKeyId;

    String accessKeySecret;

    String ossURL;

    String fileTemp;

    String imgPre;

    String domain;

    String mobileProject;

    String backendProject;

    String websocket;

    Boolean debugMode;

    String rsIncr;

    String freshCallNum;

    String timeMac;

    public static SystemConfig getInstance(){
        return (SystemConfig) SpringApplicationContextHolder.getSpringBean("systemConfig");
    }
}
