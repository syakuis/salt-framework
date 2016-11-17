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

	private ModuleContext moduleContext;
	private Module module;

	@Before
	public void init() throws IOException {
		moduleContext = new CacheModuleContext(cacheManager.getCache("module"));
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
		moduleContext.save("demo", module);

		assertEquals(module, moduleContext.get("demo"));
		assertNotSame(module.toMap(), moduleContext.get("demo").toMap());

		// 새로운 데이터를 만들어 캐시 머지 테스트
		Map<String, Object> map = moduleContext.get("demo").toMap();
		map.put("skin", null);

		moduleContext.merge("demo", new ModuleMap(map), false);

		assertNotSame(moduleContext.get("demo").toMap(), moduleContext.get("demo").toMap());
	}

	// 필수값 null 인 경우 익셉션 발생
	@Test(expected = IllegalArgumentException.class)
	public void moduleRequired() {
		Map<String, Object> map = moduleContext.get("demo").toMap();
		map.put("moduleId", null);

		moduleContext.merge("demo", new ModuleMap(map), false);
	}

	// moduleCache merge
	@Test
	public void merge() {

		Map<String, Object> map = moduleContext.get("demo").toMap();
		map.put("skin", null);
		map.put("time", null);
		moduleContext.merge("demo", new ModuleMap(map), false);

		Module dataNull = moduleContext.get("demo");

		assertNull(dataNull.getSkin());
		assertNull(dataNull.getOption("time"));

		// isNull 이 false 경우
		map.put("skin", "demo");
		map.put("time", "good");
		moduleContext.merge("demo", new ModuleMap(map), false);

		map.put("skin", null);
		map.put("time", null);
		moduleContext.merge("demo", new ModuleMap(map), true);

		Module dataNotNull = moduleContext.get("demo");

		assertNotNull(dataNotNull.getSkin());
		assertNotNull(dataNotNull.getOption("time"));
	}
}
