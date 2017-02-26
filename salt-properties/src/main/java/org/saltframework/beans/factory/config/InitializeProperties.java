package org.saltframework.beans.factory.config;

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
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public final class InitializeProperties {
	private static final Logger logger = LoggerFactory.getLogger(InitializeProperties.class);
	private static final String CHARSET_NAME = "charset";

	private final Environment environment;
	private final String[] locations;
	private String fileEncoding = Charset.defaultCharset().name();
	private Properties properties;

	protected InitializeProperties(Environment environment, String[] locations) {
		this.environment = environment;
		this.locations = locations;
	}

	protected void setFileEncoding(String fileEncoding) {
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

	protected void afterPostProcessor() {

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

			for(Resource resource : resources) {
				logger.warn("Load successful. {}", resource.getURI().toString());
			}

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

			BootstrapIntro.print(properties);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected Properties getProperties() {
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
}
