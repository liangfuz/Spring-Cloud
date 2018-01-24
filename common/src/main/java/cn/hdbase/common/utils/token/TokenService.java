package cn.hdbase.common.utils.token;

import cn.hdbase.common.config.SystemConfig;
import cn.hdbase.common.config.TokenConfig;
import cn.hdbase.common.utils.Contants;
import cn.hdbase.common.utils.redis.RedisService;
import cn.hdbase.common.utils.weixin.WeiXinToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * token service
 *
 * @author jiangrujie
 * @create 2017-09-13 上午 10:28
 **/
@Slf4j
@Component
@EnableScheduling
public class TokenService {
    private static String access_token = TokenConfig.getInstance().getRedisTokenName();

    @Autowired
    private RedisService redisService;

    @Scheduled(fixedDelay=60*60*1000)
    public void freshWeiXinToken() {
        if(!SystemConfig.getInstance().getDebugMode()) {
            if (redisService.getCacheValue(access_token) == null) {
                String token = WeiXinToken.freshToken();
                if (StringUtils.isNotEmpty(token)) {
                    log.info("save token: " + token);
                    redisService.setCacheValue(access_token, 60*60, token, Contants.REDIS_DB_INDEX_TOKEN);
                } else { //当token为空时，再一次获取
                    token = WeiXinToken.freshToken();
                    if (StringUtils.isNotEmpty(token)) {
                        log.info("save token again : " + token);
                        redisService.setCacheValue(access_token, 60*60, token, Contants.REDIS_DB_INDEX_TOKEN);
                    }
                }
            }
        }
    }

    public String getWxToken() {
        if(SystemConfig.getInstance().getDebugMode()) {
            Jedis jedis = new Jedis(TokenConfig.getInstance().getRedisHost(), TokenConfig.getInstance().getRedisPort());
            jedis.auth(TokenConfig.getInstance().getRedisPassword());
            jedis.select(Contants.REDIS_DB_INDEX_TOKEN);
            log.info("get token for debug: "+jedis.get(access_token));
            return jedis.get(access_token);
        }
        log.info("get token: "+redisService.getCacheValue(access_token,Contants.REDIS_DB_INDEX_TOKEN));
        return redisService.getCacheValue(access_token,Contants.REDIS_DB_INDEX_TOKEN);
    }
}
