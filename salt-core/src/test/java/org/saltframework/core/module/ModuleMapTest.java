package org.saltframework.core.module;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleMapTest {
	private static final Logger logger = LoggerFactory.getLogger(ModuleMapTest.class);


	@Test
	public void init() throws IOException {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("demo.module.properties"));

		ModuleMap module = new ModuleMap(properties);

		logger.debug("groupId: {}", module.getGroupId());
		logger.debug("moduleId: {}", module.getModuleId());
		logger.debug("moduleName: {}", module.getModuleName());

		Map<String, Object> original = module.toMap();
		original.put("moduleId", "demo2");

		ModuleMap copy = new ModuleMap(original);

		logger.debug(module.toString());
		logger.debug(copy.toString());

		for(Option option : copy.options()) {
			logger.debug("Option {} : {}", option.getName(), option.getValue());
			logger.debug("getOption {} : {}", option.getName(), copy.getOption(option.getName()));
		}

	}
}
