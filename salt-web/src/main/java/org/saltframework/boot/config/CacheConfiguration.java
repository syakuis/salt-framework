package org.saltframework.boot.config;

import org.saltframework.beans.factory.DataSharedFactoryBean;
import org.saltframework.data.DataSharedCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {
	private SimpleCacheManager cacheManager;

	@Bean
	public CacheManager cacheManager() {
		this.cacheManager = new SimpleCacheManager();

		cacheManager.setCaches(
				Arrays.asList(
						new ConcurrentMapCache("mainCache"),
						new ConcurrentMapCache("dataShared")
				)
		);
		return cacheManager;
	}

	@Bean
	public DataSharedCacheManager dataSharedCacheManager() {
		return new DataSharedFactoryBean(this.cacheManager).getObject();
	}
}
