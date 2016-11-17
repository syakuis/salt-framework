package org.saltframework.core.beans;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.saltframework.core.AppPostProcessor;
import org.saltframework.core.ApplicationProperties;
import org.saltframework.core.ApplicationType;
import org.saltframework.core.InitializingApplicationProperties;
import org.saltframework.core.module.ModuleMap;
import org.saltframework.core.module.ModulePostProcessor;

import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public class InitializingApplicationContextTest {

	private ApplicationProperties applicationContext;
	private String[] locations = new String[]{
			"classpath*:test/modules/**/application.properties"
	};

	@Before
	public void init() throws Exception {
		InitializingApplicationProperties initializingApplicationContext = new InitializingApplicationProperties(
				new ModulePostProcessor(),
				new AppPostProcessor()
		);
		initializingApplicationContext.setConfigLocations(locations);
		initializingApplicationContext.afterPostProcessor();
		this.applicationContext = initializingApplicationContext.getApplicationContext();
	}

	@Test
	public void moduleMap() {
		for(Properties properties : this.applicationContext.getProperties(ApplicationType.MODULE)) {
			Map<String, Object> module = new ModuleMap(properties).toMap();

			for(Map.Entry<Object, Object> item : properties.entrySet()) {
				Assert.assertEquals(module.get(item.getKey()), item.getValue());
			}
		}
	}
}
