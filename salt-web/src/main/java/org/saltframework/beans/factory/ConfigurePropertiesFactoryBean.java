package org.saltframework.beans.factory;

import org.saltframework.boot.Config;
import org.saltframework.boot.properties.InitializingConfigureProperties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * salt.properties 를 spring bean 으로 생성한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 29.
 */
public class ConfigurePropertiesFactoryBean implements FactoryBean<Config>, EnvironmentAware, ServletContextAware {
	private ServletContext servletContext;
	private Environment environment;
	private String fileEncoding;

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * Config properties.
	 * Java OPT -Dspring.profiles.active=test
	 *
	 * @return the properties
	 */
	@Override
	public Config getObject() {

		String[] locations = new String[]{
				"classpath:org/saltframework/config/salt.properties",
				"classpath:config.properties",
				"classpath:config-%s.properties"
		};

		InitializingConfigureProperties initializingGeneralProperties = new InitializingConfigureProperties(servletContext, environment, locations);
		initializingGeneralProperties.setFileEncoding(fileEncoding);
		initializingGeneralProperties.afterPostProcessor();
		return initializingGeneralProperties.getConfig();
	}

	@Override
	public Class<Config> getObjectType() {
		return Config.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
