package com.blog.shiro;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

public class ShiroSpringCacheManager implements CacheManager, Destroyable {

	private org.springframework.cache.CacheManager cacheManager;
	

	public org.springframework.cache.CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void destroy() throws Exception {
		cacheManager=null;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (name==null) {
			return null;
		}
		return new ShiroSpringCache<K,V>(name, getCacheManager());
	}

}
