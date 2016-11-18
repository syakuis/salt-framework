package org.saltframework.core.beans;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.saltframework.core.properties.AppPostProcessor;
import org.saltframework.core.properties.ApplicationProperties;
import org.saltframework.core.properties.ApplicationType;
import org.saltframework.core.properties.InitializingApplicationProperties;
import org.saltframework.core.module.ModuleMap;
import org.saltframework.core.module.ModulePostProcessor;

import java.util.Map;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public class InitializingApplicationPropertiesTest {

	private ApplicationProperties applicationProperties;
	private String[] locations = new String[]{
			"classpath*:test/modules/**/application.properties"
	};

	@Before
	public void init() throws Exception {
		InitializingApplicationProperties initializingApplicationProperties = new InitializingApplicationProperties(
				new ModulePostProcessor(),
				new AppPostProcessor()
		);
		initializingApplicationProperties.setConfigLocations(locations);
		initializingApplicationProperties.afterPostProcessor();
		this.applicationProperties = initializingApplicationProperties.getApplicationProperties();
	}

	@Test
	public void moduleMap() {
		for(Properties properties : this.applicationProperties.getProperties(ApplicationType.MODULE)) {
			Map<String, Object> module = new ModuleMap(properties).toMap();

			for(Map.Entry<Object, Object> item : properties.entrySet()) {
				Assert.assertEquals(module.get(item.getKey()), item.getValue());
			}
		}
	}
}
