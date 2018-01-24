package cn.hdbase.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Administrator on 2016/12/8.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=5*60*60)
public class HttpSessionConfig {

}
