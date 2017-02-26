package org.saltframework.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 2. 26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		SaltPropertiesConfiguration.class,
		SaltPropertiesConfigurationTest.class
})
@ActiveProfiles("test")
public class SaltPropertiesTest {
	private static final Logger logger = LoggerFactory.getLogger(SaltPropertiesTest.class);

	@Autowired
	Properties config;

	@Autowired
	Properties original;

	@Autowired
	Properties salt;

	@Autowired
	Properties profile;

	private void print(Properties props, String propsName) {
		Iterator<String> iterator = props.stringPropertyNames().iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();
			logger.debug("{} ===> {} : {}", propsName, name, props.getProperty(name));
		}
	}

	@Test
	public void properties() {
		Assert.assertNotSame(config.getProperty("config.test"), "profile");
		Assert.assertNotSame(original.getProperty("config.test"), "");
		Assert.assertNotSame(salt.getProperty("config.test"), "salt");
		Assert.assertNotSame(profile.getProperty("config.test"), "profile");
		print(config, "config");
		print(original, "original");
		print(salt, "salt");
		print(profile, "profile");
	}
}
