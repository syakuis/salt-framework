package org.saltframework.core.beans.factory;

import org.saltframework.core.properties.InitializingConfigureProperties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * 프레임워크를 구동하기 위한 설정 정보를 로드 한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public class ConfigurePropertiesFactoryBean implements FactoryBean<Properties>, EnvironmentAware {

	private Environment environment;
	private String fileEncoding;

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * Config properties.
	 * Java OPT -Dspring.profiles.active=test
	 *
	 * @return the properties
	 */
	@Override
	public Properties getObject() {

		String[] locations = new String[]{
				"classpath*:org/saltframework/config/core.properties",
				"classpath*:org/saltframework/config/web.properties",
				"classpath*:/**/_module.properties",
				"classpath*:/**/_plugin.properties",
				"classpath*:/**/_config.properties",
				"classpath*:/**/_config-%s.properties"
		};


		InitializingConfigureProperties initializingGeneralProperties = new InitializingConfigureProperties(environment, locations);
		initializingGeneralProperties.setFileEncoding(fileEncoding);
		initializingGeneralProperties.afterPostProcessor();
		return initializingGeneralProperties.getProperties();
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
