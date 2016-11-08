package org.saltframework.core.beans.factory;

import org.saltframework.core.module.ModuleCache;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 모듈 캐시를 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleCacheFactoryBean extends AbstractFactoryBean<ModuleCache> {
	private final String cache_name = "module";
	private final String propertiesName = ".module.properties";
	private final CacheManager cacheManager;
	private String configLocation;
	private String[] configLocations;

	public ModuleCacheFactoryBean(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setConfigLocation(String configLocation) {
		this.setConfigLocations(StringUtils.tokenizeToStringArray(configLocation, ","));
	}

	public void setConfigLocations(String[] configLocations) {
		this.configLocations = configLocations;
	}

	@Override
	public Class<ModuleCache> getObjectType() {
		return ModuleCache.class;
	}

	@Override
	protected ModuleCache createInstance() throws IOException {
		ModuleCache moduleCache = new ModuleCache(cacheManager.getCache(cache_name));
		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] resources = pathResourcePatternResolver.getResources(configLocations);
		for (Resource resource : resources) {
			String name = resource.getFilename().replace(propertiesName, "");
			Properties properties = new Properties();
			properties.load(resource.getInputStream());

			moduleCache.put(name, properties);
		}

		return moduleCache;
	}
}
