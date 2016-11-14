package org.saltframework.core.module;

import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleCache implements Serializable {
	private static final long serialVersionUID = 5614004969079250948L;
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

	/**
	 * 두 객체를 병합한다. 병합 처리시 null 값은 항목이 삭제되거나 항목 병합이 무시된다.
	 *
	 * @param name   the name
	 * @param module the module
	 * @return the module
	 */
	public Module merge(String name, Map<String, Object> module) {
		return merge(name, module, true);
	}
	/**
	 * 두 객체를 병합한다. 병합 처리시 null 값은 항목이 삭제되거나 항목 병합이 무시된다.
	 *
	 * @param name   the name
	 * @param module the module
	 * @param isNull null 값 허용여부. true : 옵션의 항목이 제거된다, false : 옵션의 항목 병합이 무시된다. 기존에 값을 그대로 유지한다.
	 * @return the module
	 * null 대입
	 * null 제외하고 대입
	 * null 제거하기 ( 다른 방식으로 처리)
	 */
	public Module merge(String name, Map<String, Object> module, boolean isNull) {
		Module original = cache.get(name, Module.class);
		Map<String, Object> result = original.toMap();

		if (isNull) {
			result.putAll(module);
		} else {
			for(Map.Entry<String, Object> item : module.entrySet()) {
				Object value = item.getValue();

				if (value != null) {
					result.put(item.getKey(), value);
				}
			}
		}

		cache.put(name, new ModuleMap(result));

		return cache.get(name, Module.class);
	}
}
