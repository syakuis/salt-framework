package org.saltframework.core.beans.factory;

import org.saltframework.core.module.CacheModuleContext;
import org.saltframework.core.module.Module;
import org.saltframework.core.module.ModuleContext;
import org.saltframework.core.module.ModuleMap;
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
public class ModuleCacheFactoryBean extends AbstractFactoryBean<ModuleContext> {
	private static final String CACHE_NAME = "module";
	private static final String PROPERTIES_NAME = ".module.properties";
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
	public Class<ModuleContext> getObjectType() {
		return ModuleContext.class;
	}

	@Override
	protected ModuleContext createInstance() throws IOException {
		ModuleContext moduleContext = new CacheModuleContext(cacheManager.getCache(CACHE_NAME));
		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] resources = pathResourcePatternResolver.getResources(configLocations);
		for (Resource resource : resources) {
			Properties properties = new Properties();
			properties.load(resource.getInputStream());

			Module module = new ModuleMap(properties);
			String name = module.getModuleName();

			moduleContext.save(name, module);
		}

		return moduleContext;
	}

	// cache name
	// CommonsContext = commons
	// moduleContext = module
	// pluginContext = plugin
	// layoutContext -- 나중에 작업 = layout

	// 로드순서
	// 1. 프로퍼티
	// 2. 디비
}
