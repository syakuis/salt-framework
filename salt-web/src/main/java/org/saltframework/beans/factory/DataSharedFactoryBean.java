package org.saltframework.beans.factory;

import org.saltframework.data.DataSharedCacheManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.CacheManager;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 7.
 */
public class DataSharedFactoryBean implements FactoryBean<DataSharedCacheManager> {
	private final CacheManager cacheManager;
	private String cacheName = "dataShared";

	public DataSharedFactoryBean(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public DataSharedCacheManager getObject() {
		return new DataSharedCacheManager(cacheManager.getCache(cacheName));
	}

	@Override
	public Class<DataSharedCacheManager> getObjectType() {
		return DataSharedCacheManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
