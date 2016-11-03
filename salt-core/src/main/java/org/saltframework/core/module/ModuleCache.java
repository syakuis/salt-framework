package org.saltframework.core.module;

import org.springframework.cache.Cache;

import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleCache {

	private final Cache cache;

	public ModuleCache(Cache cache) {
		this.cache = cache;
	}

	public Module get(String name) {
		return cache.get(name, Module.class);
	}

	public void put(String name, Properties module) {
		cache.put(name, new FactoryModule(module));
	}

	public void put(String name, Map<String, Object> module) {
		cache.put(name, new FactoryModule(module));
	}

	public Module merge(String name, Map<String, Object> module) {
		Module module2 = cache.get(name, Module.class);
		Map<String, Object> module3 = module2.getOptions();
		module3.putAll(module);
		cache.put(name, new FactoryModule(module3));

		return new FactoryModule(module3);
	}
}
