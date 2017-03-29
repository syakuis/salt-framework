package org.saltframework.boot;

import org.saltframework.beans.factory.config.ConfigurePropertiesConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 4. 21.
 */

@Configuration
@ComponentScan(
		basePackages = "org.saltframework.boot.config",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class BootstrapContext {
	@Autowired
	private Properties config;

	@Bean
	public static ConfigurePropertiesConfigurer configurePropertiesConfigurer() {
		return new ConfigurePropertiesConfigurer();
	}
}
