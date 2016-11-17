package org.saltframework.core.boot.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 16.
 */
public class FirstBeanTestPostProcessor implements
		BeanNameAware,
		BeanFactoryAware,
		EnvironmentAware,
		ApplicationContextAware,
		BeanDefinitionRegistryPostProcessor, BeanPostProcessor,
		BeanClassLoaderAware,
		ResourceLoaderAware,
		InitializingBean,
		FactoryBean<BeanTest> {
	private static final Logger logger = LoggerFactory.getLogger(FirstBeanTestPostProcessor.class);

	@Override
	public void setBeanName(String name) {
		System.out.println("========== First BeanNameAware.setBeanName ====>" + name);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("========== First BeanFactoryAware.setBeanFactory ====>" + beanFactory.toString());
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("========== First BeanClassLoaderAware.setBeanClassLoader ====>" + classLoader.toString());
	}

	@Override
	public BeanTest getObject() throws Exception {
		System.out.println("========== First FactoryBean.getObject ====> object");
		return new BeanTest();
	}

	@Override
	public Class<BeanTest> getObjectType() {
		System.out.println("========== First FactoryBean.getObject ====> type");
		return BeanTest.class;
	}

	@Override
	public boolean isSingleton() {
		System.out.println("========== First FactoryBean.getObject ====> singleton");
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("========== First InitializingBean.afterPropertiesSet ====> ");
	}

	/*
			@Configuration 모두 생성되고 호출된다 : Order = 0

			speing environment
		 */
	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("========== First EnvironmentAware.setEnvironment ====> " + environment.toString());
	}

	// @Configuration 이 모두 인스턴스되고 호출된다. Order : 0
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("========== First ResourceLoaderAware.setResourceLoader ====> " + resourceLoader.toString());
	}

	// @Configuration 이 모두 인스턴스되고 호출된다. Order : 2
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("========== First BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry ===>" + registry.toString());

		//RootBeanDefinition beanDefinition = new RootBeanDefinition(BeanLifecycle.class); //The service implementation
		//registry.registerBeanDefinition("book", beanDefinition);
	}

	// @Configuration 이 모두 프럭시되고 호출된다. Order : 3
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("========== First BeanFactoryPostProcessor.postProcessBeanFactory ===>" + beanFactory.toString());
	}

	// @Configuration 이 모두 인스턴스되고 호출된다. Order : 1
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("========== First ApplicationContextAware.setApplicationContext ===>" + applicationContext.toString());
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("========== First BeanPostProcessor.postProcessAfterInitialization ===>" + beanName);
		return bean;
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("========== First BeanPostProcessor.postProcessBeforeInitialization ====>" + beanName);
		return bean;
	}
}
