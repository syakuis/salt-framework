package org.saltframework.beans.factory.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * 프레임워크를 구동하기 위한 설정 정보를 로드 한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public class SaltPropertiesFactroryBean implements FactoryBean<Properties>, EnvironmentAware {

	private String[] locations = new String[]{
			"classpath*:org/saltframework/config/salt.properties",
			"classpath*:salt.properties",
			"classpath*:salt-%s.properties"
	};
	private String fileEncoding;
	private Environment environment;

	public void setLocation(String location) {
		this.locations = StringUtils.commaDelimitedListToStringArray(location);
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

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
		InitializeProperties initializingProperties = new InitializeProperties(environment, locations);
		initializingProperties.setFileEncoding(fileEncoding);
		initializingProperties.afterPostProcessor();
		return initializingProperties.getProperties();
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
