package org.saltframework.config;

import org.saltframework.beans.factory.config.SaltPropertiesFactroryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 2. 26.
 */
@Configuration
public class SaltPropertiesConfigurationTest {
	@Autowired
	private Environment evn;

	@Bean("original")
	public Properties original() {
		SaltPropertiesFactroryBean saltPropertiesFactroryBean = new SaltPropertiesFactroryBean();
		saltPropertiesFactroryBean.setEnvironment(evn);
		saltPropertiesFactroryBean.setLocation("classpath*:org/saltframework/config/salt.properties");

		return saltPropertiesFactroryBean.getObject();
	}

	@Bean("salt")
	public Properties salt() {
		SaltPropertiesFactroryBean saltPropertiesFactroryBean = new SaltPropertiesFactroryBean();
		saltPropertiesFactroryBean.setEnvironment(evn);
		saltPropertiesFactroryBean.setLocation("classpath*:org/saltframework/config/salt.properties,classpath*:salt.properties");

		return saltPropertiesFactroryBean.getObject();
	}

	@Bean("profile")
	public Properties profile() {
		SaltPropertiesFactroryBean saltPropertiesFactroryBean = new SaltPropertiesFactroryBean();
		saltPropertiesFactroryBean.setEnvironment(evn);
		saltPropertiesFactroryBean.setLocation("classpath*:org/saltframework/config/salt.properties,classpath*:salt.properties,classpath*:salt-%s.properties");

		return saltPropertiesFactroryBean.getObject();
	}
}
