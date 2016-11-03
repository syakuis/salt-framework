package org.saltframework.core.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootstrapConfiguration.class)
public class BootstrapTest {
	private static final Logger logger = LoggerFactory.getLogger(BootstrapTest.class);

	@Autowired
	Properties config;

	@Test
	public void properties() {
		Iterator<String> iterator = config.stringPropertyNames().iterator();

		while (iterator.hasNext()) {
			String name = iterator.next();

			logger.debug(name + " : " + config.getProperty(name));
		}
	}
}
