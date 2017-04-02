package org.saltframework.boot.config.support;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageSourceMatchingPatternTest {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceMatchingPattern.class);

	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

	@Autowired ResourceLoader resourceLoader;

	@Test
	public void getResources() throws IOException {

		String[] locationPattern = {
				"classpath:org/saltframework/i18n/message.properties",
				"classpath:org/saltframework/**/i18n/message.properties"
		};

		for (String path : locationPattern) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);
			System.out.println(path + "(" + resources.length + ")");
			for (Resource resource : resources) {
				System.out.println(resourceLoader.getResource(resource.getURI().getPath()).getURI().getPath());

			}
		}

	}

}
