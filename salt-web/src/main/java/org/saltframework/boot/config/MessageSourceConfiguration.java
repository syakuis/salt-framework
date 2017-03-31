package org.saltframework.boot.config;

import org.saltframework.beans.factory.MessageSourceFactoryBean;
import org.saltframework.boot.Config;
import org.saltframework.util.io.MessageSourceMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 31.
 */
@Configuration
public class MessageSourceConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfiguration.class);

	@Autowired
	private Config config;

	private MessageSource messageSourceParent;
	private MessageSource messageSource;

	private ResourceBundleMessageSource getResourceBundleMessageSource(MessageSource parentMessageSource) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setCacheSeconds(cacheSeconds);
		messageSource.setDefaultEncoding(defaultEncoding);
		if (parentMessageSource != null) messageSource.setParentMessageSource(parentMessageSource);
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(
						"------> MessageSourceAccessor: " +
								" cacheSeconds: " + cacheSeconds +
								" , defaultEncoding: " + defaultEncoding
				);
			}

			MessageSourceMatchingPattern messageSourceMatchingPattern = new MessageSourceMatchingPattern();
			String[] resources = messageSourceMatchingPattern.getResources(basenames);
			messageSource.setBasenames(resources);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	private ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource(MessageSource parentMessageSource) {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(cacheSeconds);
		messageSource.setDefaultEncoding(defaultEncoding);
		messageSource.setConcurrentRefresh(concurrentRefresh);
		if (parentMessageSource != null) messageSource.setParentMessageSource(parentMessageSource);
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(
						"------> MessageSourceAccessor: " +
								" cacheSeconds: " + cacheSeconds +
								" , defaultEncoding: " + defaultEncoding
				);
			}
			String[] resources = PathMatchingClassPath.getClassPath(basenames);
			messageSource.setBasenames(resources);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	@Bean
	public MessageSource messageSourceParent() {
		return getReloadableResourceBundleMessageSource();
	}

	@Bean
	@DependsOn("messageSourceParent")
	public MessageSource messageSource() {
		return getReloadableResourceBundleMessageSource(messageSourceParent);
	}

}
