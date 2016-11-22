package org.saltframework.config.context;

import org.saltframework.core.beans.factory.ApplicationPropertiesFactoryBean;
import org.saltframework.core.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 21.
 */
@Configuration
public class ApplicationPropertiesConfiguration {

	@Autowired
	Properties config;

	@Bean("app")
	public ApplicationProperties applicationProperties() {
		ApplicationPropertiesFactoryBean factoryBean = new ApplicationPropertiesFactoryBean(config.getProperty("application.packages"));
		return factoryBean.getObject();
	}
}
