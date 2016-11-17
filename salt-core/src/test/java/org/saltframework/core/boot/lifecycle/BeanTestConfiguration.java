package org.saltframework.core.boot.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 16.
 */
@Configuration
public class BeanTestConfiguration {

	public BeanTestConfiguration() {
		System.out.println(" BookConfiguration constructor ===>");
	}

	@Bean
	public FirstBeanTestPostProcessor firstBeanTestPostProcessor() {
		System.out.println(" BeanTestConfiguration firstBeanTestPostProcessor ===>");
		return new FirstBeanTestPostProcessor();
	}

	@Bean
	public SecondBeanTestPostProcessor secondBeanTestPostProcessor() {
		System.out.println(" BeanTestConfiguration secondBeanTestPostProcessor ===>");
		return new SecondBeanTestPostProcessor();
	}
}
