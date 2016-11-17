package org.saltframework.core.boot.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
public class FindTest implements
		InitializingBean,
		BeanFactoryPostProcessor, BeanPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("--------------------------------------------> postProcessBeanFactory");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("--------------------------------------------> 1");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("--------------------------------------------> before");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("--------------------------------------------> after");
		return bean;
	}
}
