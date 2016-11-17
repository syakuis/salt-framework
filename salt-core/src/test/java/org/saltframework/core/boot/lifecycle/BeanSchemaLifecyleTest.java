package org.saltframework.core.boot.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * ServletContextAware
 * ApplicationContextAware
 * BeanDefinitionRegistryPostProcessor
 * EnvironmentCapable
 * ResourceLoaderAware
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BootstrapConfiguration.class, BeanSchemaTestConfiguration.class})
@ActiveProfiles("test")
public class BeanSchemaLifecyleTest {

	//@Autowired
	//BeanLifecycle beanLifecycle;

	@Resource(name = "beanTest")
	CreateBeanTest createBeanTest;

	@Autowired
	@Qualifier(value = "firstBeanTestPostProcessor")
	BeanTest firstBeanTestPostProcessor;

	@Autowired
	@Qualifier(value = "secondBeanTestPostProcessor")
	BeanTest secondBeanTestPostProcessor;


	@Test
	public void test() {
	}
}
