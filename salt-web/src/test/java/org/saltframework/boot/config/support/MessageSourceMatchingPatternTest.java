package org.saltframework.boot.config.support;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.saltframework.util.io.ResourcesMatchingPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * {@link PathMatchingResourcePatternResolver} 를 이용하여 모든 메세지 프로퍼티를 찾는 다
 * 찾은 메세지 프로퍼티의 패키지 경로만 얻기 위해 {@link Resource#createRelative} 편법을 이용했다.
 *
 * @see PathMatchingResourcePatternResolver
 * @see Resource
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 2.
 */
public class MessageSourceMatchingPatternTest {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceMatchingPattern.class);

	private static final String RESOURCE_DESCRIPTION_PATTERN = "class path resource \\[(.*)\\]";

	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

	@Test
	public void getResources() throws Exception {

		String[] locationPatterns = {
				"classpath:org/saltframework/*/message.properties"
		};

		for (String path : locationPatterns) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);

			for (Resource resource : resources) {
				System.out.println(resource.getURI().getPath());
				String s = resource.createRelative("/message").getDescription().replaceAll(RESOURCE_DESCRIPTION_PATTERN, "$1");
				System.out.println(s);

				System.out.println(IOUtils.toString(resource.getInputStream(), "utf-8"));

				InputStream patha = resource.getInputStream();
				System.out.println(new ClassPathResource("/").getPath());
				System.out.println(StringUtils.applyRelativePath(resource.getURI().getPath(), "/message.propeties"));
				System.out.println(resource.createRelative("").getFile().getAbsolutePath());
				System.out.println(resource.getFile().getAbsolutePath());
				System.out.println(resource.getFile().getCanonicalPath());
				System.out.println(ClassUtils.convertResourcePathToClassName(resource.getURI().getPath()));
				//System.out.println(new ClassPathResource(resource);
				System.out.println(this.getClass().getClassLoader().getResource("file:" + resource.getFile().getAbsolutePath()));
			}
		}

	}

}
