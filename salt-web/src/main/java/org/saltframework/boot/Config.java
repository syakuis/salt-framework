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
	private Properties properties;
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
		this.properties = properties;
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

	public String getString(String name) {
		return getString(name, null);
	}

	public String getString(String name, String defaultValue) {
		if (defaultValue == null) {
			return this.properties.getProperty(name);
		}

		String value = this.properties.getProperty(name);
		return StringUtils.isEmpty(value) ? defaultValue : value;
	}

	public int getInt(String name) {
		String value = getString(name, "0");
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name) {
		String value = getString(name, "false");
		return Boolean.parseBoolean(value);
	}
}
