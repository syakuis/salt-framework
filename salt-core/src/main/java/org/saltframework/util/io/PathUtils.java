package org.saltframework.util.io;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 9.
 */
public final class PathUtils {
	private static final String CLASSES_PATH = "/WEB-INF/classes";

	private PathUtils() {
	}

	public static String getWebRootAbsolutePath() {
		File file = new File( PathUtils.class.getResource("/").getPath() );
		String url = FilenameUtils.separatorsToSystem( file.getAbsolutePath() );
		String classpath = FilenameUtils.separatorsToSystem(CLASSES_PATH);
		return url.replace(classpath, "");
	}

}
