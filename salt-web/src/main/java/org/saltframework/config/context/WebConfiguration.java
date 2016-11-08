package org.saltframework.config.context;

import org.saltframework.config.BootstrapConfiguration;
import org.saltframework.core.beans.factory.DataSourceInitializerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
@Configuration
@ComponentScan(
		basePackages = "org.saltframework.apps",
		useDefaultFilters = false,
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class),
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
		}
)
@Import(BootstrapConfiguration.class)
public class WebConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Properties config;

	@Bean("webDataSourceInitializer")
	@Profile("create-db")
	public DataSourceInitializer dataSourceInitializerFactoryBean() throws Exception {
		DataSourceInitializerFactoryBean factoryBean = new DataSourceInitializerFactoryBean(dataSource);
		return factoryBean.getObject();
	}

	@Bean(name ="freemarkerConfig")
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/WEB-INF/apps/views/");
		configurer.setDefaultEncoding(config.getProperty("config.charset"));

		Properties properties = new Properties();
		properties.setProperty("cache_storage", "freemarker.cache.NullCacheStorage");
		properties.setProperty("auto_import", "spring.ftl as spring");
		properties.setProperty("number_format", "0.####");
		configurer.setFreemarkerSettings(properties);

		return configurer;
	}
}
