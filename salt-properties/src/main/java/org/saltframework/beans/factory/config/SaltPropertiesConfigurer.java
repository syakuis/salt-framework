package org.saltframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 18.
 */
public class SaltPropertiesConfigurer implements BeanDefinitionRegistryPostProcessor {
	private String beanName = "config";

	public SaltPropertiesConfigurer() {
		super();
	}

	public SaltPropertiesConfigurer(String beanName) {
		super();
		this.beanName = beanName;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		registry.registerBeanDefinition(beanName, new RootBeanDefinition(SaltPropertiesFactroryBean.class));
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}
}
