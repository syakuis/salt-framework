package org.saltframework.boot.config;

import org.saltframework.boot.properties.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = "org.saltframework.apps",
		useDefaultFilters = false,
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
						ControllerAdvice.class,
						Controller.class
				})
		}
)
public class WebServletConfiguration extends WebMvcConfigurerAdapter implements WebMvcConfigurer {
	@Autowired private Config config;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] resources = config.getResourcesPath();

		for (String resource : resources) {
			registry.addResourceHandler(String.format(config.getLocale(), "%s/**", resource))
					.addResourceLocations(String.format(config.getLocale(), "%s/", resource))
					.setCachePeriod(config.getCacheSeconds());
		}
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebContentInterceptor wci = new WebContentInterceptor();
		wci.setCacheSeconds(config.getCacheSeconds());
		registry.addInterceptor(wci);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		if ("jsp".equals(config.getViewResolverType())) {
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setContentType("text/html; charset=" + config.getCharset() +";");
			viewResolver.setCache(config.isViewResolverCache());
			viewResolver.setPrefix(config.getViewResolverPrefix());
			viewResolver.setSuffix(config.getViewResolverSuffix());

			registry.viewResolver(viewResolver);
		} else {
			FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
			viewResolver.setExposeSpringMacroHelpers(config.isFreemarkerExposeSpringMacroHelpers());

			viewResolver.setContentType("text/html; charset=" + config.getCharset() +";");
			viewResolver.setCache(config.isViewResolverCache());
			viewResolver.setPrefix(config.getViewResolverPrefix());
			viewResolver.setSuffix(config.getViewResolverSuffix());

			registry.viewResolver(viewResolver);
		}
	}
}
