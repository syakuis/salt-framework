package org.saltframework.config.context;

import org.apache.commons.lang3.ArrayUtils;
import org.saltframework.core.beans.factory.ModuleCacheFactoryBean;
import org.saltframework.core.module.ModuleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

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
	public ModuleContext moduleCache() throws Exception {

		String[] configLocations = ArrayUtils.addAll(
				StringUtils.tokenizeToStringArray(config.getProperty("config.module.configLocations"), ","),
				StringUtils.tokenizeToStringArray(config.getProperty("module.configLocations"), ","));

		ModuleCacheFactoryBean factoryBean = new ModuleCacheFactoryBean(cacheManager);
		factoryBean.setConfigLocations(configLocations);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}