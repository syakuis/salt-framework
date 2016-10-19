package org.saltframework.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 6. 7.
 */
public final class PathMatchingClassPath {
	private static final Logger logger = LoggerFactory.getLogger(PathMatchingClassPath.class);

	private PathMatchingClassPath() {
	}

	public static String[] getClassPath(String locationPattern) throws IOException {
		return getClassPath(new String[]{locationPattern});
	}

	public static String[] getClassPath(String[] locationPattern) throws IOException {
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

		List<String> strings = new ArrayList();

		for (String path : locationPattern) {
			Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);
			for (Resource resource : resources) {
				try {
					String uri = resource.getURI().toString();
					String baseName = null;

					if (resource instanceof FileSystemResource) {
						// baseName = "classpath:" + substringBetween(uri, "/classes/", ".properties");
						baseName = substringBefore(uri, ".properties");
					} else if (resource instanceof ClassPathResource) {
						baseName = substringBefore(uri, ".properties");
					} else if (resource instanceof UrlResource) {
						baseName = "classpath:" + substringBetween(uri, ".jar!/", ".properties");
					}

					if (baseName != null) {
						baseName = processBasename(baseName);
						strings.add(baseName);
					}

					if (logger.isDebugEnabled()) {
						logger.debug("Resource classpath Loaded: " + uri + " => " + baseName);
					}

				} catch (FileNotFoundException e) {
					logger.error(e.getMessage());
				}
			}
		}

		return strings.toArray(new String[strings.size()]);
	}

	private static String processBasename(String baseName) {
		String prefix = substringBeforeLast(baseName, "/");
		String name = substringAfterLast(baseName, "/");
		do {
			name = substringBeforeLast(name, "_");
		} while (name.contains("_"));
		return prefix + "/" + name;
	}
}
