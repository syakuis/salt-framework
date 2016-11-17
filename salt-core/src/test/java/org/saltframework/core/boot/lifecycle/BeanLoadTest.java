package org.saltframework.core.boot.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 16.
 */
public class BeanLoadTest implements  InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware, FactoryBean<BeanTest> {
	private static final Logger logger = LoggerFactory.getLogger(BeanLoadTest.class);

	private String benaName;
	public BeanLoadTest() {
		System.out.println("---BeanLifecycle constructor---");
	}

	@Override
	public BeanTest getObject() throws Exception {
		System.out.println("---BeanLifecycle getObject---");
		return new BeanTest();
	}

	@Override
	public Class<BeanTest> getObjectType() {
		return BeanTest.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("---BeanClassLoaderAware.setBeanClassLoader---");
	}
	@Override
	public void setBeanName(String name) {
		System.out.println("---BeanNameAware.setBeanName---");
	}
	public void myPostConstruct() {
		System.out.println("---init-method---");
	}
	@PostConstruct
	public void springPostConstruct() {
		System.out.println("---@PostConstruct---");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("---BeanFactoryAware.setBeanFactory---");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("---InitializingBean.afterPropertiesSet---");
	}
	public String getBenaName() {
		return benaName;
	}
	public void setBenaName(String benaName) {
		this.benaName = benaName;
		System.out.println("setBenaName: BeanLifecycle name has set.");
	}
	public void myPreDestroy() {
		System.out.println("---destroy-method---");
	}
	@PreDestroy
	public void springPreDestroy() {
		System.out.println("---@PreDestroy---");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("---DisposableBean.destroy---");
	}
	@Override
	protected void finalize() {
		System.out.println("---inside finalize---");
	}
}
