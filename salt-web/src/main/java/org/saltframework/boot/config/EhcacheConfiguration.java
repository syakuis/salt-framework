package org.saltframework.boot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@EnableCaching
public class EhcacheConfiguration {
	@Bean("dataShareCacheManager")
	public CacheManager dataShareCacheManager() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setCacheManagerName("dataShare");
		factoryBean.setShared(true);

		return new EhCacheCacheManager(factoryBean.getObject());
	}
}
