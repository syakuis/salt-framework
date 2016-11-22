package org.saltframework.config.context;

import org.saltframework.core.beans.factory.ModuleCacheFactoryBean;
import org.saltframework.core.module.ModuleContext;
import org.saltframework.core.properties.ApplicationProperties;
import org.saltframework.core.properties.ApplicationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Configuration
public class ModuleContextConfiguration {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Bean
	public ModuleContext moduleContext() throws Exception {

		ModuleCacheFactoryBean factoryBean = new ModuleCacheFactoryBean(
				cacheManager,
				applicationProperties.getProperties(ApplicationType.MODULE));
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}