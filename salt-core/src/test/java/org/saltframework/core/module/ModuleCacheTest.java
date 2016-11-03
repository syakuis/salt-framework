package org.saltframework.core.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootstrapConfiguration.class)
public class ModuleCacheTest {

	@Autowired
	private ModuleCache moduleCache;

	@Test
	public void test() throws IOException {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("org/saltframework/apps/modules/demo/demo.module.properties"));

		System.out.println(ModuleName.moduleId);

		Module module = new FactoryModule(properties);
		Module module2 = new FactoryModule((Map<String, Object>) properties.clone());

		System.out.printf("%s\n%s\n", module, module2);

		for (Option option : module2.options()) {
			System.out.printf("%s : %s\n", option.getName(), option.getValue());
		}
	}

	@Test
	public void cache() {
		System.out.println(moduleCache.get("demo"));
	}
}
