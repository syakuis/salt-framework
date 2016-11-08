package egovmei.config;

import org.mybatis.spring.annotation.MapperScan;
import org.saltframework.support.mybatis.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
@Configuration
@ComponentScan(
		basePackages = "egovmei.apps",
		useDefaultFilters = false,
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class),
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
		}
)
@MapperScan(basePackages = "egovmei.apps", annotationClass = Mapper.class)
@ImportResource({"egovmei/bootstrap-context.xml"})
public class ConfigContext {
}
