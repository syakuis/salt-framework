package org.saltframework.boot.config;

import org.saltframework.boot.Config;
import org.saltframework.boot.config.support.MessageSourceMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 31.
 */

public class MessageSourceConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfiguration.class);
	private MessageSource messageSource;
	private String parentBasename =
			"classpath:org/saltframework/i18n/message.properties," +
			"classpath:org/saltframework/**/i18n/message.properties";

	@Autowired
	private Config config;

	private MessageSourceMatchingPattern messageSourceMatchingPattern = new MessageSourceMatchingPattern();

	private ResourceBundleMessageSource getResourceBundleMessageSource(String basename, MessageSource parentMessageSource) {
		String[] basenames = StringUtils.commaDelimitedListToStringArray(basename);

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setCacheSeconds(config.getInt("messageSourceCacheSeconds"));
		messageSource.setDefaultEncoding(config.getCharset());
		if (parentMessageSource != null) messageSource.setParentMessageSource(parentMessageSource);
		try {
			messageSource.setBasenames(messageSourceMatchingPattern.getResources(basenames));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	private ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource(String basename, MessageSource parentMessageSource) {
		String[] basenames = StringUtils.commaDelimitedListToStringArray(basename);

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(config.getInt("messageSourceCacheSeconds"));
		messageSource.setDefaultEncoding(config.getCharset());
		messageSource.setConcurrentRefresh(config.getBoolean("messageSourceConcurrentRefresh"));
		if (parentMessageSource != null) messageSource.setParentMessageSource(parentMessageSource);
		try {
			messageSource.setBasenames(messageSourceMatchingPattern.getResources(basenames));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return messageSource;
	}

	private MessageSource getMessageSource() {
		if (config.getBoolean("messageSourceReloadable")) {
			return getReloadableResourceBundleMessageSource(parentBasename,
					getReloadableResourceBundleMessageSource(config.getString("messageSourceBasename"), null));
		}

		return getResourceBundleMessageSource(parentBasename,
				getResourceBundleMessageSource(config.getString("messageSourceBasename"),null));
	}

	@Bean
	public MessageSource messageSource() {
		this.messageSource = getMessageSource();
		return this.messageSource;
	}

	@Bean
	@DependsOn("messageSource")
	public MessageSourceAccessor messageSourceAccessor() {
		return new MessageSourceAccessor(this.messageSource);
	}
}
