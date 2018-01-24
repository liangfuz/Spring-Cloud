package cn.hdbase.common.utils.redis;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;
import redis.clients.util.SafeEncoder;

import javax.annotation.Resource;
import java.util.*;

@Component
public class RedisService
{

    @Resource
    StringRedisTemplate stringRedisTemplate;
    

    public String getCacheValue(final String key, final Integer dbIndex)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<String>()
        {

            public String doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                byte[] temp = connection.get(key.getBytes());
                if (temp != null)
                {
                    return SafeEncoder.encode(temp);
                }
                return null;
            }
        });
    }
    

    public void setCacheValue(final String key, final String value, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.set(key.getBytes(), value.getBytes());
                return null;
            }
        });
    }
    

    public String getCacheValue(final String key)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<String>()
        {

            public String doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                byte[] temp = connection.get(key.getBytes());
                if (temp != null)
                {
                    return SafeEncoder.encode(temp);
                }
                return null;
            }
        });
    }
    

    public void setCacheValue(final String key, final String value)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.set(key.getBytes(), value.getBytes());
                return null;
            }
        });
    }
    

    public void setCacheValue(final String key, final Integer seconds, final String value, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.setEx(key.getBytes(), seconds, value.getBytes());
                return null;
            }
        });
    }
    
    /**
     * 
     * 更新失效时间
     */

    public void setExpire(final String key, final Integer seconds, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.expire(key.getBytes(), seconds);
                return null;
            }
        });
    }
    

    public void delBykey(final Integer dbIndex, final String key)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.del(key.getBytes());
                return null;
            }
        });
    }
    
    /**
     * 清空数据库 param:Integer dbIndex(数据库的标识)
     */
    public void redisFlushdb(final Integer dbIndex)
    {
        
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.flushDb();
                return null;
            }
        });
    }
    

    public List<String> getAllKeys(final Integer dbIndex)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<List<String>>()
        {

            public List<String> doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                List<String> result = new ArrayList<String>();
                connection.select(dbIndex);
                Set<byte[]> keys = connection.keys("*".getBytes());
                for (byte[] key : keys)
                {
                    result.add(SafeEncoder.encode(key));
                }
                return result;
            }
        });
    }
    

    public void hashMapSetValue(final String mapKey, final String key, final String value, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {

            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.hSet(mapKey.getBytes(), key.getBytes(), value.getBytes());
                return null;
            }
        });
    }
    

    public void hashMapSetValue(final String mapKey, final Map<byte[], byte[]> value, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.hMSet(mapKey.getBytes(), value);
                return null;
            }
        });
        
    }
    

    public String hashMapGetValue(final String mapKey, final String key, final Integer dbIndex)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<String>()
        {
            public String doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                byte[] temp = connection.hGet(mapKey.getBytes(), key.getBytes());
                if (temp != null)
                {
                    return SafeEncoder.encode(temp);
                }
                return null;
            }
        });
    }
    

    public Map<String, String> hashMapGetAllValue(final String mapKey, final Integer dbIndex)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<Map<String, String>>()
        {

            public Map<String, String> doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                Map<String, String> result = new HashMap<String, String>();
                connection.select(dbIndex);
                Map<byte[], byte[]> temp = connection.hGetAll(mapKey.getBytes());
                
                for (Map.Entry<byte[], byte[]> entry : temp.entrySet())
                {
                    result.put(SafeEncoder.encode(entry.getKey()), SafeEncoder.encode(entry.getValue()));
                }
                return result;
            }
        });
    }
    

    public void hashMapMgetAllValue(final String[] mapKeys, final Integer dbIndex)
    {
        
    }
    

    public void hashMapDelByKey(final String mapKey, final String key, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.hDel(mapKey.getBytes(), key.getBytes());
                return null;
            }
        });
    }
    

    public void hashSetAddValue(final String setKey, final String value, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.sAdd(setKey.getBytes(), value.getBytes());
                return null;
            }
        });
    }
    

    public void hashSetDelMember(final String setKey, final String member, final Integer dbIndex)
    {
        this.stringRedisTemplate.execute(new RedisCallback<Object>()
        {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                connection.sRem(setKey.getBytes(), member.getBytes());
                return null;
            }
        });
        
    }
    

    public Long hashSetCount(final String setKey, final Integer dbIndex)
    {
        return this.stringRedisTemplate.execute(new RedisCallback<Long>()
        {
            public Long doInRedis(RedisConnection connection)
                throws DataAccessException
            {
                connection.select(dbIndex);
                return connection.sCard(setKey.getBytes());
            }
        });
        
    }
    

    public ValueOperations<String, String> getOpsForValue()
    {
        // TODO Auto-generated method stub
        return stringRedisTemplate.opsForValue();
    }

    public Integer incr(String key){
        RedisAtomicInteger counter = new RedisAtomicInteger(key, stringRedisTemplate.getConnectionFactory());
        return counter.incrementAndGet();

//        RedisConnection redisConnection=null;
//        try {
//            redisConnection= stringRedisTemplate.getConnectionFactory().getConnection();
//            Integer re=redisConnection.incr(key.getBytes()).intValue();
//            return re;
//        }finally {
//            if(redisConnection!=null&&!redisConnection.isClosed()) {
//                redisConnection.close();
//            }
//        }

    }
    @SuppressWarnings("unchecked")
    public void deletePatter(String patter){
        Set<String>set = stringRedisTemplate.keys(patter);
        if(!CollectionUtils.isEmpty(set)) {
                stringRedisTemplate.delete(set);
        }
    }
}
