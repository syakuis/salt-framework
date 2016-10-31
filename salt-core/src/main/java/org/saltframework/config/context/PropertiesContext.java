package org.saltframework.config.context;

import org.saltframework.beans.factory.GeneralPropertiesFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 6. 7.
 */
@Configuration
public class PropertiesContext {

	@Autowired
	Environment env;

	/**
	 * Config properties.
	 * Java OPT -Dspring.profiles.active=test
	 *
	 * @return the properties
	 * @throws IOException the io exception
	 */
	@Bean
	public Properties config() throws IOException {
		GeneralPropertiesFactoryBean bean = new GeneralPropertiesFactoryBean(env);
		return bean.getObject();
	}
}
