package org.saltframework.boot.config;

import org.saltframework.boot.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * 어플리케이션 영역의 자원들을 관리한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Configuration
@ComponentScan(
		basePackages = "org.saltframework.apps",
		useDefaultFilters = false,
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
						Component.class,
						Service.class
				}),
		}
)
public class WebApplicationConfiguration {
	@Autowired private Config config;

	@Bean(name ="freemarkerConfig")
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths(
				"classpath:/META-INF/resources/WEB-INF/views/",
				config.getFreemarkerTemplateLoaderPath()
		);
		configurer.setDefaultEncoding(config.getCharset());

		Properties properties = new Properties();
		properties.setProperty("cache_storage", "freemarker.cache.NullCacheStorage");
		properties.setProperty("auto_import", "spring.ftl as spring");
		properties.setProperty("number_format", "0.####");
		configurer.setFreemarkerSettings(properties);

		return configurer;
	}
}
