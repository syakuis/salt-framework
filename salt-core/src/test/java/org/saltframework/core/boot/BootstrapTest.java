package org.saltframework.core.boot;

import org.junit.Test;
import org.saltframework.core.module.ModuleContext;
import org.saltframework.core.properties.ApplicationProperties;
import org.saltframework.core.properties.ApplicationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 8.
 */
public class BootstrapTest extends BootstrapTestCase {
	private static final Logger logger = LoggerFactory.getLogger(BootstrapTest.class);

	@Autowired
	Properties config;

	@Autowired
	ApplicationProperties app;

	@Autowired
	ModuleContext moduleContext;

	@Test
	public void properties() {
		Iterator<String> iterator = config.stringPropertyNames().iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();

			logger.debug(name + " : " + config.getProperty(name));
		}

		List<Properties> modules = app.getProperties(ApplicationType.MODULE);
		modules.size();
		for(Properties properties : modules) {
			String moduleId = properties.getProperty("moduleId");

			logger.debug(moduleContext.get(moduleId).toMap().toString());
		}
	}
}
