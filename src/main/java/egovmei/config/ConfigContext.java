package egovmei.config;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
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
		basePackages = "egovmei",
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class),
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class)
		}
)
@MapperScan(basePackages = "egovmei", annotationClass = Mapper.class)
@ImportResource("egovmei/config/config-context.xml")
public class ConfigContext {
}
