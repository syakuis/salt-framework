package org.saltframework.core.properties;

import org.saltframework.util.io.PathMatchingResourceResolver;
import org.saltframework.util.object.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

/**
 * application.properties 찾아서 어플리케이션 형태를 나눠서 담는 다.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public class InitializingApplicationProperties {
	private static final Logger logger = LoggerFactory.getLogger(InitializingApplicationProperties.class);

	private Map<String, List<Properties>> applicationMap = new LinkedHashMap<>();
	private ApplicationProperties applicationProperties = new ApplicationProperties();
	private final ApplicationPostProcessor[] applicationPostProcessors;

	private String[] configLocations;

	public void setConfigLocations(String... configLocations) {
		this.configLocations = configLocations;
	}

	public InitializingApplicationProperties(ApplicationPostProcessor... applicationPostProcessors) {
		Assert.notEmpty(applicationPostProcessors);
		this.applicationPostProcessors = applicationPostProcessors;
	}

	public ApplicationProperties getApplicationProperties() {
		return this.applicationProperties;
	}

	public void afterPostProcessor() {
		Assert.notEmpty(this.configLocations);

		List<Properties> properties = this.getProperties();

		for(ApplicationPostProcessor applicationPostProcessor : applicationPostProcessors) {

			ApplicationType applicationType = applicationPostProcessor.getType();

			addApplicationProperties(applicationType, properties);

			this.applicationProperties.addProperties(
					applicationType,
					translate(applicationPostProcessor.getSearchKey(), getApplicationProperties(applicationType))
			);

		}
	}

	private List<Properties> getProperties() {
		List<Properties> result = new ArrayList<>();

		try {
			PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
			Resource[] resources = pathResourcePatternResolver.getResources(this.configLocations);

			for(Resource resource : resources) {
				Properties properties = new Properties();
				properties.load(resource.getInputStream());
				result.add(properties);

				logger.warn("Read the resources. {}", resource.getURI().toString());
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 읽어진 프로퍼티를 규칙을 적용하여 새로운 프로퍼티를 생성한다.
	 * 규칙 : key 에 해당하는 프로퍼티명을 찾아 새로운 프로퍼티에 추가한다. 그리고 프로퍼티명에서 key 와 일치하는 문자는 제거된다.
	 *
	 * @param key        the key
	 * @param properties the properties
	 * @return the list
	 */
	private List<Properties> translate(String key, List<Properties> properties) {
		List<Properties> result = new ArrayList<>();

		for(Properties primary : properties) {
			List<String> names = PropertiesUtils.getNames(primary, key);
			Properties second = new Properties();
			for(String name : names) {
				second.put(name.replaceFirst(key, ""), primary.getProperty(name));
			}
			result.add(second);
		}

		return result;
	}

	private void addApplicationProperties(ApplicationType applicationType, List<Properties> properties) {
		this.applicationMap.put(applicationType.name(), properties);
	}

	private List<Properties> getApplicationProperties(ApplicationType applicationType) {
		return applicationMap.get(applicationType.name());
	}
}
