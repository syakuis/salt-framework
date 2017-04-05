package org.saltframework.boot.config;

import org.junit.Test;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 1.
 */
public class MessageSourceConfigurationTest {
	@Test
	public void resourceBundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setCacheSeconds(0);
		messageSource.setDefaultEncoding("UTF-8");

		messageSource.setBasenames("org/saltframework/i18n/message");
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		System.out.println(messageSourceAccessor.getMessage("text.confirm.test"));
	}

	@Test
	public void reloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(0);
		messageSource.setDefaultEncoding("UTF-8");

		messageSource.setBasenames("org/saltframework/i18n/message");
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		System.out.println(messageSourceAccessor.getMessage("text.confirm.test"));
	}
}
