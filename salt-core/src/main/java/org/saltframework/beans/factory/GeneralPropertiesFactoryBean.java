package org.saltframework.beans.factory;


import org.apache.commons.lang3.StringUtils;
import org.saltframework.beans.Profile;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

/**
 * 프레임워크를 구동하기 위한 프로퍼티를 모두 로드한다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 21.
 */
public class GeneralPropertiesFactoryBean implements FactoryBean<Properties> {
	private final Environment env;

	private boolean singleton = true;
	private boolean localOverride = true;
	private boolean ignoreResourceNotFound = true;
	private String fileEncoding = Charset.defaultCharset().name();

	public GeneralPropertiesFactoryBean(Environment env) {
		this.env = env;
	}

	public void setLocalOverride(boolean localOverride) {
		this.localOverride = localOverride;
	}

	public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	@Override
	public Properties getObject() throws IOException {
		String profile;

		if (fileEncoding == null) fileEncoding = "UTF-8";

		String[] profiles = env.getActiveProfiles();
		if (profiles != null) {
			if (profiles.length == 0) profiles = env.getDefaultProfiles();
		}

		if (env.acceptsProfiles(Profile.DEV.value)) {
			profile = Profile.DEV.value;
		} else if (env.acceptsProfiles(Profile.TEST.value)) {
			profile = Profile.TEST.value;
		} else if (env.acceptsProfiles(Profile.PROD.value)) {
			profile = Profile.PROD.value;
		} else {
			profile = Profile.DEFAULT.value;
		}

		String[] props = new String[]{
				"classpath*:org/saltframework/config/salt.properties",
				"classpath*:salt.properties",
				"classpath*:salt-" + profile + ".properties"
		};

		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] resources = pathResourcePatternResolver.getResources(props);

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocations(resources);
		propertiesFactoryBean.setIgnoreResourceNotFound(ignoreResourceNotFound); // 프로퍼티 파일이 없는 경우 무시한다.
		propertiesFactoryBean.setFileEncoding(fileEncoding);
		propertiesFactoryBean.setLocalOverride(localOverride); // 중복의 프로퍼티인 경우 나중에 프로퍼티를 사용한다.
		propertiesFactoryBean.setSingleton(singleton); // 싱글톤 여부 기본값 true

		propertiesFactoryBean.afterPropertiesSet();

		Properties properties = propertiesFactoryBean.getObject();

		properties.setProperty("app.profiles", StringUtils.join(profiles, ","));
		properties.setProperty("app.profile", profile);
		properties.setProperty("app.charset", fileEncoding);

		properties.setProperty("app.timeZone", TimeZone.getDefault().getID());

		Locale locale = Locale.getDefault();
		properties.setProperty("app.locale", locale.getLanguage());

		return properties;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}