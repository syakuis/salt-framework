package org.saltframework.boot.config;

import org.junit.Test;
import org.saltframework.boot.config.support.MessageSourceMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 1.
 */
public class MessageSourceConfigurationTest {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceConfigurationTest.class);
	private MessageSourceMatchingPattern messageSourceMatchingPattern = new MessageSourceMatchingPattern();

	private String[] parentBasenames = {"classpath:org/saltframework/i18n/message.properties",
			"classpath:org/saltframework/**/i18n/message.properties"};

	@Test
	public void test() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setCacheSeconds(0);
		messageSource.setDefaultEncoding("UTF-8");

		messageSource.setBasenames("org/saltframework/i18n/message");
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		System.out.println(messageSourceAccessor.getMessage("text.confirm.test"));
	}
}
