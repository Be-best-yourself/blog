package com.blog.shiro;


import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;
@SuppressWarnings("unchecked")
public class ShiroSpringCache<K,V> implements org.apache.shiro.cache.Cache<K, V>{

	private static final Logger logger = LoggerFactory.getLogger(ShiroSpringCache.class);
	private CacheManager cacheManager;
	private Cache cache;
	
	public ShiroSpringCache(String name,CacheManager cacheManager){
		if (name==null|| cacheManager==null) {
			try {
				throw new IllegalAccessException("cacheManage or CacheName cannot null.");
			} catch (IllegalAccessException e) {
				logger.info(e.toString());;
			}
		}
		this.cacheManager=cacheManager;
		this.cache=cacheManager.getCache(name);
	}

	@Override
	public V get(K key) throws CacheException {
	//	logger.info("从缓存中获取key为｛"+key+"｝的缓存信息",key);
		if (key==null) {
			return null;
		}
		ValueWrapper valueWrapper = cache.get(key);
		if (valueWrapper==null) {
			return null;
		}
		return (V) valueWrapper.get();
	}

	@Override
	public V put(K key, V value) throws CacheException {
		//logger.info("创建新的缓存，信息为：｛"+key+"｝=｛"+value+"｝",key,value);
		cache.put(key, value);
		return get(key);
	}

	@Override
	public V remove(K key) throws CacheException {
		//logger.info("移除key为｛｝的缓存",key);
		V v = get(key);
		cache.evict(key);
		return v;
	}

	@Override
	public void clear() throws CacheException {
		//logger.info("清空缓存");
		cache.clear();
	}

	@Override
	public int size() {
		return cacheManager.getCacheNames().size();
	}

	@Override
	public Set<K> keys() {
		//logger.info("获取缓存中所有的key值");
		return (Set<K>) cacheManager.getCacheNames();
	}

	@Override
	public Collection<V> values() {
		//logger.info("获取缓存中所有的values值");
		return (Collection<V>) cache.get(cacheManager.getCacheNames()).get();
	}

}
