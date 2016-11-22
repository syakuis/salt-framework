package org.saltframework.config;

import org.saltframework.core.beans.factory.config.ConfigurePropertiesConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 4. 21.
 */

@Configuration
@ComponentScan(
		basePackages = "org.saltframework.config.context",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class BootstrapConfiguration {
	@Bean
	public static ConfigurePropertiesConfigurer configurePropertiesConfigurer() {
		return new ConfigurePropertiesConfigurer();
	}
}
