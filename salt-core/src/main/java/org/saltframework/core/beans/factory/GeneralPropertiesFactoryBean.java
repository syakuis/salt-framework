package org.saltframework.core.beans.factory;


import org.apache.commons.lang3.StringUtils;
import org.saltframework.core.beans.Profile;
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
 * 프레임워크를 구동하기 위한 모든 프로퍼티를 로드한다.
 *
 * 1. 기본 프로퍼티 로드 : 서비스 환경에 따른 프로퍼티 로드 -> 프레임워크 개발자
 * 2. 서브파티 프로퍼티 로드 -> 프레임워크 개발자
 * 3. 모듈 및 플러그인 프로퍼티 로드 -> 모듈 개발자
 * 4. 애플리케이션 프로퍼티 로드 : 상위 설정을 오버로딩할 수 있다. -> 프로젝트
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 21.
 */
public class GeneralPropertiesFactoryBean implements FactoryBean<Properties> {
	private final Environment env;

	private final static boolean singleton = true;
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

		if (fileEncoding == null) {
			fileEncoding = "UTF-8";
		}
		String[] profiles = env.getActiveProfiles();
		if (profiles != null && profiles.length == 0) {
			profiles = env.getDefaultProfiles();
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
				"classpath*:org/saltframework/config/config.properties",
				"classpath*:config.properties",
				"classpath*:config-" + profile + ".properties"
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

		properties.setProperty("config.profiles", StringUtils.join(profiles, ","));
		properties.setProperty("config.profile", profile);
		properties.setProperty("config.charset", fileEncoding);

		properties.setProperty("config.timeZone", TimeZone.getDefault().getID());

		Locale locale = Locale.getDefault();
		properties.setProperty("config.locale", locale.getLanguage());

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