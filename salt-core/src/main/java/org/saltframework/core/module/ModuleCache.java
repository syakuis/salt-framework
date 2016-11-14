package org.saltframework.core.module;

import org.springframework.cache.Cache;

import java.util.List;
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
		cache.put(name, new ModuleMap(module));
	}

	public void put(String name, Map<String, Object> module) {
		cache.put(name, new ModuleMap(module));
	}

	public Module merge(String name, Map<String, Object> module) {
		Module original = cache.get(name, Module.class);
		Map<String, Object> result = original.toMap();
		result.putAll(module);
		cache.put(name, new ModuleMap(result));

		return cache.get(name, Module.class);
	}
}
