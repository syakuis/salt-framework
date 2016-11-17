package org.saltframework.core.boot.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@ContextConfiguration(classes = {BootstrapConfiguration.class, BeanTestConfiguration.class})
@ActiveProfiles("test")
public class BeanLifecyleTest {

	@Autowired
	BeanTest firstBeanTestPostProcessor;

	@Autowired
	BeanTest secondBeanTestPostProcessor;

	@Test
	public void test() {
	}
}
