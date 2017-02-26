package org.saltframework.config;

import org.saltframework.beans.factory.config.SaltPropertiesConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 2. 26.
 */
@Configuration
public class SaltPropertiesConfiguration {
	@Bean
	public static SaltPropertiesConfigurer saltPropertiesConfigurer() {
		return new SaltPropertiesConfigurer();
	}
}
