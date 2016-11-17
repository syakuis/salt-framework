package org.saltframework.core.module;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleMapTest {
	@Test
	public void test() throws IOException {
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

		String moduleId = properties.getProperty("moduleId");
		String moduleName = properties.getProperty("moduleName");

		// ModuleMap 생성되는 지 확인한다.
		Module module = new ModuleMap(properties);
		assertNotNull("should not be null", module);

		// 값이 일치하는 지 확인한다.
		assertEquals("failure - strings are not equal", moduleId, module.getGroupId());
		assertEquals("failure - strings are not equal", moduleId, module.getModuleId());
		assertEquals("failure - strings are not equal", moduleName, module.getModuleName());

		// Map 으로 변경하여 얻는 다.
		Map<String, Object> original = module.toMap();
		assertNotNull("should not be null", original);

		String aModuleId = "demo2";
		assertFalse("failure - should be false", original.isEmpty());
		original.put("moduleId", aModuleId);

		Module copy = new ModuleMap(original);
		assertNotNull("should not be null", copy);

		assertNotSame("should not be same Object", module, copy);
		assertEquals("failure - strings are not equal", aModuleId, copy.getModuleId());

		for(Option option : copy.options()) {
			assertEquals("failure - strings are not equal", option.getValue(), copy.getOption(option.getName()));
		}

	}
}
