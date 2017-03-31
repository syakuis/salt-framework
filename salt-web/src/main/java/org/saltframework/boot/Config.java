package org.saltframework.boot;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * salt.properties to Config.class
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Getter
@ToString
public final class Config {
	private TimeZone timeZone;
	private Locale locale;

	private final String version = "1.0.0.RC1";
	private String profile;
	private String[] profiles;
	private String charset;
	private int cacheSeconds;

	private String rootAbsolutePath;

	private String[] resourcesPath;

	private boolean viewResolverCache;

	private String freemarkerTemplateLoaderPath;
	private boolean freemarkerExposeSpringMacroHelpers;

	private Config() {
	}

	public Config(Properties properties, TimeZone timeZone, Locale locale) {
		this.timeZone = timeZone;
		this.locale = locale;

		profile = properties.getProperty("profile");
		profiles = StringUtils.delimitedListToStringArray(properties.getProperty("profiles"), ",");
		charset = properties.getProperty("charset");
		cacheSeconds = Integer.parseInt(properties.getProperty("cacheSeconds"));

		rootAbsolutePath = properties.getProperty("rootAbsolutePath");

		resourcesPath = StringUtils.delimitedListToStringArray(properties.getProperty("resourcesPath"), ",");

		viewResolverCache = Boolean.parseBoolean(properties.getProperty("viewResolverCache"));

		freemarkerTemplateLoaderPath = properties.getProperty("freemarkerTemplateLoaderPath");
		freemarkerExposeSpringMacroHelpers = Boolean.parseBoolean(properties.getProperty("freemarkerExposeSpringMacroHelpers"));
	}
}
