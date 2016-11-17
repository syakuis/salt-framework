package org.saltframework.core.module;

import org.saltframework.util.io.PathUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 9.
 */
public final class ModuleUtils {
	private ModuleUtils() {
	}

	public static List<String> getSkinsFolderName(String module) {
		File skinFile = new File(PathUtils.getWebRootAbsolutePath() + "/" + module);
		if (!skinFile.exists()) {
			return Collections.EMPTY_LIST;
		}

		List<String> result = new ArrayList<>();
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+");

		File[] files = skinFile.listFiles();

		if (files == null || files.length == 0) {
			return result;
		}

		for(File file : files) {
			if (file.isDirectory() && pattern.matcher(file.getName()).find()) {
				result.add(file.getName());
			}
		}

		return result;
	}
}
