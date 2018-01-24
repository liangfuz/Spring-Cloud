package cn.dingshen.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Administrator on 2016/12/8.
 */
@Configuration
public class RedisConfig {

    @Autowired
    SystemConfig systemConfig;

    @Bean
    public JedisConnectionFactory connectionFactory(JedisPoolConfig jedisPoolConfig)
    {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setPort(Integer.parseInt(systemConfig.getRedisPort()));
        connection.setHostName(systemConfig.getRedisHost());
        if(!StringUtils.isBlank(systemConfig.getRedisPassword())){
            connection.setPassword(systemConfig.getRedisPassword());
        }
//        connection.setHostName("10.26.89.2");
//        connection.setPassword("dsycredis");
        connection.setPoolConfig(jedisPoolConfig);
        connection.setUsePool(true);
        return connection;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxTotal(500);
        jedisPoolConfig.setMaxWaitMillis(15000);
        jedisPoolConfig.setMinIdle(30);
        jedisPoolConfig.setTestOnBorrow(true);
        //在将连接放回池中前，自动检验连接是否有效
        jedisPoolConfig.setTestOnReturn(true);
        //自动测试池中的空闲连接是否都是可用连接
        jedisPoolConfig.setTestWhileIdle(true);
//        //逐出连接的最小空闲时间
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(30000);
//        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
//        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(30000);
//        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
//        //每次逐出的最大连接数
//        jedisPoolConfig.setNumTestsPerEvictionRun(100);
        return jedisPoolConfig;
    }
    @Bean(name="redisTemplate")
    public RedisTemplate<byte[], Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<byte[], Object> template = new RedisTemplate<byte[], Object>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
    @Bean(name="redisStringTemplate")
    public StringRedisTemplate redisStringTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
