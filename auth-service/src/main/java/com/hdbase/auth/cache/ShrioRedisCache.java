package com.hdbase.auth.cache;


import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

@Slf4j
public class ShrioRedisCache<K, V> implements Cache<K, V> {
//	private Logger log = LogManager.getLogger(getClass());
	private RedisTemplate<String, V> redisTemplate;
	private String prefix = "shiro_redis:";

	public ShrioRedisCache(RedisTemplate<String, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public ShrioRedisCache(RedisTemplate<String, V> redisTemplate, String prefix) {
		this(redisTemplate);
//		this.prefix = prefix;
	}


	public V get(K key) throws CacheException {
		if(key == null) {
			return null;
		}
		String bkey = getByteKey(key);
		return redisTemplate.opsForValue().get(bkey);
	}


	public V put(K key, V value) throws CacheException {
		if(key == null || value == null) {
			return null;
		}

		String bkey = getByteKey(key);
		redisTemplate.opsForValue().set(bkey, value);
		return value;
	}


	public V remove(K key) throws CacheException {
		if(key == null) {
			return null;
		}
		K keyv=(K)key.toString();
		String bkey = getByteKey(keyv);
		ValueOperations<String, V> vo = redisTemplate.opsForValue();
		V value = vo.get(bkey);
		redisTemplate.delete(bkey);
		return value;
	}

	public void removeSession(String key) throws CacheException {
		if(key == null) {
			return;
		}
		String bkey = "spring:session:sessions:"+key;

		ValueOperations<String, V> vo = redisTemplate.opsForValue();
		V value = vo.get(bkey);
		System.out.println(bkey);
		System.out.println(value);
		redisTemplate.delete(bkey);
//		return value;
	}

	public void clear() throws CacheException {
//		redisTemplate.getConnectionFactory().getConnection().flushDb();
		String bkey = (prefix+"*");
		Set<String> set = redisTemplate.keys(bkey);
		if(!CollectionUtils.isEmpty(set)) {
			for(String key:set) {
				redisTemplate.delete(key);
			}
		}
	}

	public int size() {
//		Long len = redisTemplate.getConnectionFactory().getConnection().dbSize();
//		return len.intValue();
		String bkey = (prefix+"*");
		Set<String> set = redisTemplate.keys(bkey);
		return set.size();
	}

	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		String bkey = (prefix+"*");
		Set<String> set = redisTemplate.keys(bkey);
		Set<K> result = Sets.newHashSet();
		
		if(CollectionUtils.isEmpty(set)) {
			return Collections.emptySet();
		}
		
		for(String key: set) {
			result.add((K)key);
		}
		return result;
	}


	public Collection<V> values() {
		Set<K> keys = keys();
		List<V> values = new ArrayList<V>(keys.size());
		for(K k: keys) {
			String bkey = getByteKey(k);
			values.add(redisTemplate.opsForValue().get(bkey));
		}
		return values;
	}
	
	private String getByteKey(K key){
		String preKey = this.prefix + key;
		return preKey;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}