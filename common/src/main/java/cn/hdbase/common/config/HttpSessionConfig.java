package cn.hdbase.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by Administrator on 2016/12/8.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=5*60*60)
public class HttpSessionConfig {
    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        strategy.setCookieSerializer(new CustomerCookiesSerializer());
        return strategy;
    }
}
