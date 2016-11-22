package org.saltframework.core.beans.factory;

import org.saltframework.core.properties.AppPostProcessor;
import org.saltframework.core.properties.ApplicationProperties;
import org.saltframework.core.properties.InitializingApplicationProperties;
import org.saltframework.core.module.ModulePostProcessor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

/**
 * 어플리케이션 영역의 모든 초기 설정 정보를 로드하여 설정 정보를 초기화한다.
 * 어플리케이션 영역은 모듈, 플러그인, 레이아웃 그리고 공통에 해당하는 설정정보이다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
public class ApplicationPropertiesFactoryBean implements FactoryBean<ApplicationProperties> {
	private static final String PROPERTIES = "classpath*:%s/application.properties";
	private final String[] basePackages;

	public ApplicationPropertiesFactoryBean(String basePackages) {
		this.basePackages = StringUtils.delimitedListToStringArray(basePackages, ",");
	}

	@Override
	public ApplicationProperties getObject() {

		InitializingApplicationProperties initializingApplicationContext = new InitializingApplicationProperties(
				new ModulePostProcessor(),
				new AppPostProcessor()
		);
		initializingApplicationContext.setConfigLocations(createLocations());
		initializingApplicationContext.afterPostProcessor();
		return initializingApplicationContext.getApplicationProperties();
	}

	private String[] createLocations() {
		String[] locations = {
				String.format(PROPERTIES, "org/saltframework/apps/**")
		};

		if (basePackages != null) {
			for (int i = 0; i < basePackages.length; i++) {
				locations = StringUtils.addStringToArray(locations, String.format(PROPERTIES, basePackages[i]));
			}
		}

		return locations;
	}

	@Override
	public Class<ApplicationProperties> getObjectType() {
		return ApplicationProperties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
