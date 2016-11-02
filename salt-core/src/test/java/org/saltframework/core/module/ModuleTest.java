package org.saltframework.core.module;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public class ModuleTest {
	@Test
	public void test() throws IOException {
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream("org/saltframework/apps/modules/demo/module.properties"));

		System.out.println(ModuleName.moduleId);

		Module module = new Module((Map<String, Object>) properties.clone());
		Module module2 = new Module((Map<String, Object>) properties.clone());

		System.out.printf("%s\n%s\n", module, module2);

		for (Option option : module2.options()) {
			System.out.printf("%s : %s\n", option.getName(), option.getValue());
		}

		Option option = module.option();

	}
}
