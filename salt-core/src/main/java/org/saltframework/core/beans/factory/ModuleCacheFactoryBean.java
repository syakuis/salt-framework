package org.saltframework.core.beans.factory;

import org.saltframework.core.module.CacheModuleContext;
import org.saltframework.core.module.Module;
import org.saltframework.core.module.ModuleContext;
import org.saltframework.core.module.ModuleMap;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 모듈 캐시를 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleCacheFactoryBean extends AbstractFactoryBean<ModuleContext> {
	private static final String CACHE_NAME = "module";
	private final CacheManager cacheManager;
	private final List<Properties> properties;

	public ModuleCacheFactoryBean(CacheManager cacheManager, List<Properties> properties) {
		this.cacheManager = cacheManager;
		this.properties = properties;
	}

	@Override
	public Class<ModuleContext> getObjectType() {
		return ModuleContext.class;
	}

	@Override
	protected ModuleContext createInstance() {
		ModuleContext moduleContext = new CacheModuleContext(cacheManager.getCache(CACHE_NAME));

		for (Properties property : this.properties) {
			Module module = new ModuleMap(property);
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
