package org.saltframework.config;

import org.saltframework.core.beans.factory.ApplicationPropertiesFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
public class ApplicationPropertiesRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationPropertiesRegistryPostProcessor.class);

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		logger.debug("applicationProperties loader");
		RootBeanDefinition definition = new RootBeanDefinition(ApplicationPropertiesFactoryBean.class);
		registry.registerBeanDefinition("applicationProperties", definition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
