package org.saltframework.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
@Configuration
@ComponentScan(
		basePackages = "org.saltframework.apps",
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class),
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
		}
)
public class WebConfiguration {
}
