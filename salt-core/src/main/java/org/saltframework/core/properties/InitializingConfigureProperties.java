package org.saltframework.core.properties;

import org.apache.commons.lang3.StringUtils;
import org.saltframework.core.beans.Profile;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 프레임워크를 구동하기 위한 설정 정보를 초기화한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public class InitializingConfigureProperties {
	private static final Logger logger = LoggerFactory.getLogger(InitializingConfigureProperties.class);
	private static final String CHARSET_NAME = "charset";

	private final Environment environment;
	private final String[] locations;
	private String fileEncoding = Charset.defaultCharset().name();
	private Properties properties;

	public InitializingConfigureProperties(Environment environment, String[] locations) {
		this.environment = environment;
		this.locations = locations;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	/**
	 * 프레임워크에서 사용될 언어셋을 최종 선정한다.
	 * default = utf-8 , properties , system properties 순으로 최종적으로 설정된다.
	 */
	private void setFileEncoding() {
		if (fileEncoding == null && !"".equals(fileEncoding)) {
			fileEncoding = "UTF-8";
		}

		String charset = environment.getProperty(CHARSET_NAME);
		if (charset != null && !"".equals(charset)) {
			fileEncoding = charset;
		}
	}

	public void afterPostProcessor() {

		setFileEncoding();

		String[] profiles = environment.getActiveProfiles();
		if (profiles != null && profiles.length == 0) {
			profiles = environment.getDefaultProfiles();
		}

		String profile = getProfile(environment);

		String[] configLocations = new String[locations.length];

		for(int i = 0; i < locations.length; i++) {
			String location = String.format(locations[i], profile);
			configLocations[i] = location;
		}

		try {
			PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
			Resource[] resources = pathResourcePatternResolver.getResources(configLocations);

			PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
			propertiesFactoryBean.setLocations(resources);
			propertiesFactoryBean.setIgnoreResourceNotFound(true); // 프로퍼티 파일이 없는 경우 무시한다.
			propertiesFactoryBean.setFileEncoding(fileEncoding);
			propertiesFactoryBean.setLocalOverride(true); // 중복의 프로퍼티인 경우 나중에 프로퍼티를 사용한다.
			propertiesFactoryBean.setSingleton(true); // 싱글톤 여부 기본값 true

			propertiesFactoryBean.afterPropertiesSet();

			this.properties = propertiesFactoryBean.getObject();

			properties.setProperty("config.profiles", StringUtils.join(profiles, ","));
			properties.setProperty("config.profile", profile);
			properties.setProperty("config.charset", fileEncoding);

			properties.setProperty("config.timeZone", TimeZone.getDefault().getID());

			Locale locale = Locale.getDefault();
			properties.setProperty("config.locale", locale.getLanguage());

			intro();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Properties getProperties() {
		return this.properties;
	}

	private String getProfile(Environment env) {
		if (environment.acceptsProfiles(Profile.DEV.getValue())) {
			return Profile.DEV.getValue();
		} else if (environment.acceptsProfiles(Profile.TEST.getValue())) {
			return Profile.TEST.getValue();
		} else if (environment.acceptsProfiles(Profile.PROD.getValue())) {
			return Profile.PROD.getValue();
		} else {
			return Profile.DEFAULT.getValue();
		}
	}

	/**
	 * http://www.network-science.de/ascii/
	 * standard type smslant slant
	 */
	private void intro() {
		StringBuilder print = new StringBuilder();

		print.append("\n_________________________________________________________________________\n");
		print.append("                                                                         \n");
		print.append("   ____       __ __   ____                                          __\n");
		print.append("  / __/___ _ / // /_ / __/____ ___ _ __ _  ___  _    __ ___   ____ / /__\n");
		print.append(" _\\ \\ / _ `// // __// _/ / __// _ `//  ' \\/ -_)| |/|/ // _ \\ / __//  '_/\n");
		print.append("/___/ \\_,_//_/ \\__//_/  /_/   \\_,_//_/_/_/\\__/ |__,__/ \\___//_/  /_/\\_\\\n");
		print.append("                                                  version " + properties.getProperty("config.version"));
		print.append("\n                                                                         \n");
		print.append("                                                                         \n");
		print.append("                           Salt Framework by 52572 49437 44512   \n");
		print.append("                                                                         \n");
		print.append("_________________________________________________________________________\n\n");
		print.append("* @timeZone: " + properties.getProperty("config.timeZone"));
		print.append("\n");
		print.append("* @date: " + new Date());
		print.append("\n");
		print.append("* @java encoding: " + properties.getProperty("config.charset"));
		print.append("\n");
		print.append("* @profile: " + properties.getProperty("config.profile"));
		print.append("\n");
		print.append("* @profiles: " + properties.getProperty("config.profiles"));
		print.append("\n");
		print.append("_________________________________________________________________________\n\n");

		logger.warn(print.toString());

		if (logger.isDebugEnabled()) {
			Iterator<String> iterator = properties.stringPropertyNames().iterator();

			while (iterator.hasNext()) {
				String name = iterator.next();

				logger.debug("{} : {}", name, properties.getProperty(name));
			}
		}

	}
}
