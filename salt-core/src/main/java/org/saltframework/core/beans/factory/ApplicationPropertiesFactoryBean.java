package org.saltframework.core.beans.factory;

import org.saltframework.core.properties.AppPostProcessor;
import org.saltframework.core.properties.ApplicationProperties;
import org.saltframework.core.properties.InitializingApplicationProperties;
import org.saltframework.core.module.ModulePostProcessor;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
public class ApplicationPropertiesFactoryBean implements FactoryBean<ApplicationProperties> {

	@Override
	public ApplicationProperties getObject() throws Exception {
		String[] locations = new String[]{
				"classpath*:test/modules/**/application.properties"
		};

		InitializingApplicationProperties initializingApplicationContext = new InitializingApplicationProperties(
				new ModulePostProcessor(),
				new AppPostProcessor()
		);
		initializingApplicationContext.setConfigLocations(locations);
		initializingApplicationContext.afterPostProcessor();
		return initializingApplicationContext.getApplicationProperties();
	}

	@Override
	public Class<ApplicationProperties> getObjectType() {
		return ApplicationProperties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
