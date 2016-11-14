package org.saltframework.core.module;

import org.junit.Before;
import org.junit.Test;
import org.saltframework.core.boot.BootstrapTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 10.
 */
public class ModuleCacheTest extends BootstrapTestCase {

	@Autowired
	private CacheManager cacheManager;

	private ModuleCache moduleCache;
	private Module module;

	@Before
	public void init() throws IOException {
		moduleCache = new ModuleCache(cacheManager.getCache("module"));
		Properties properties = new Properties();

		/*
		프로퍼티의
		moduleId = demo
		moduleName = demo
		skin = tpl
		time = 10000
		title = 데모
		 */

		properties.load(ClassLoader.getSystemResourceAsStream("demo.module.properties"));

		module = new ModuleMap(properties);
		assertNotNull("should not be null", module);
	}

	// moduleCache 생성
	@Test
	public void create() throws IOException {
		// 캐시에 담음
		moduleCache.put("demo", module.toMap());

		assertNotEquals(module, moduleCache.get("demo"));
		assertNotSame(module.toMap(), moduleCache.get("demo").toMap());

		// 새로운 데이터를 만들어 캐시 머지 테스트
		Map<String, Object> map = moduleCache.get("demo").toMap();
		map.put("skin", null);

		moduleCache.merge("demo", map);

		assertNotSame(moduleCache.get("demo").toMap(), moduleCache.get("demo").toMap());
	}

	// 필수값 null 인 경우 익셉션 발생
	@Test(expected = IllegalArgumentException.class)
	public void moduleRequired() {
		Map<String, Object> map = moduleCache.get("demo").toMap();
		map.put("moduleId", null);

		moduleCache.merge("demo", map);
	}

	// moduleCache merge
	@Test
	public void merge() {

		Map<String, Object> map = moduleCache.get("demo").toMap();
		map.put("skin", null);
		map.put("time", null);
		moduleCache.merge("demo", map);

		Map<String, Object> dataIsNull = moduleCache.get("demo").toMap();

		assertNull(dataIsNull.get("skin"));
		assertNull(dataIsNull.get("time"));

		// isNull 이 false 경우
		map.put("skin", "demo");
		map.put("time", "good");
		moduleCache.merge("demo", map, false);

		map.put("skin", null);
		map.put("time", null);
		moduleCache.merge("demo", map, false);

		Map<String, Object> dataIsNullFalse = moduleCache.get("demo").toMap();

		assertNotNull(dataIsNullFalse.get("skin"));
		assertNotNull(dataIsNullFalse.get("time"));
	}
}
