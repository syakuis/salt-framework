package org.saltframework.config.context;

import org.saltframework.core.beans.factory.ModuleCacheFactoryBean;
import org.saltframework.core.module.ModuleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Configuration
public class ModuleConfiguration {

	@Autowired
	private Properties config;

	@Autowired
	private CacheManager cacheManager;

	@Bean
	public ModuleCache moduleCache() throws Exception {
		ModuleCacheFactoryBean factoryBean = new ModuleCacheFactoryBean(cacheManager);
		factoryBean.setConfigLocations(config.getProperty("module.configLocation"));
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}