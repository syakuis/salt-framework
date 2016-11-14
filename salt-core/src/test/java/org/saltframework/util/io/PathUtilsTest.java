package org.saltframework.util.io;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 9.
 */

public class PathUtilsTest {

	@Test
	public void getWebRootAbsolutePath() {
		System.out.println(PathUtils.getWebRootAbsolutePath());
	}

}
