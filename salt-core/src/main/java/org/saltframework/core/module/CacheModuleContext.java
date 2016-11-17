package org.saltframework.core.module;

import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class CacheModuleContext implements Serializable, ModuleContext {
	private static final long serialVersionUID = 5614004969079250948L;
	private final Cache cache;

	public CacheModuleContext(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Module get(String name) {
		return cache.get(name, Module.class);
	}

	@Override
	public void save(String name, Module module) {
		cache.put(name, module);
	}

	/**
	 * 두 객체를 병합한다. 병합 처리시 null 값은 항목이 삭제되거나 항목 병합이 무시된다.
	 *
	 * @param name   the name
	 * @param module the module
	 * @param ignoreNull 옵션의 value가 null 인 경우 무시 여부. false : 모두 저장된다. true : null 값은 저장되지 않는 다.
	 * @return the module
	 * null 허용
	 * null 무시
	 * null 필드인 경우 허용 아닌경우 데이터 삭제
	 *
	 * moduleMap 에서는 null 인 경우 무조건 저장되지 않음 기존값을 유지한다. - 변경 무조건 저장.
	 * 하지만 머지에서 널에대한 처리가 존재한다.
	 *
	 *
	 *
	 */
	@Override
	public Module merge(String name, Module module, boolean ignoreNull) {
		Module original = cache.get(name, Module.class);
		Map<String, Object> result = original.toMap();

		if (ignoreNull) {
			for(Map.Entry<String, Object> item : module.toMap().entrySet()) {
				Object value = item.getValue();

				if (value != null) {
					result.put(item.getKey(), value);
				}
			}
		} else {
			result.putAll(module.toMap());
		}

		cache.put(name, new ModuleMap(result));

		return cache.get(name, Module.class);
	}
}
