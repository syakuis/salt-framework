package org.saltframework.core.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 9.
 */
public final class ModuleUtils {
	public static final String MODULE_SKIN_DIR_FORMAT = "/WEB-INF/views/modules/%s";
	private ModuleUtils() {
	}

	public static List<String> getSkinsFolderName() {

		File


		List<String> listFolders = new ArrayList<String>();

		String path = moduleContext.getAbsolutePath() + String.format(SKINS_PATH, module_name);
		File folder = new File(path);
		if (!folder.exists()) return listFolders;

		File arrayFolder[] = folder.listFiles();
		int count = 0;
		if (arrayFolder != null) {
			count = arrayFolder.length;
		}

		for (int i = 0; i < count; i++) {
			File info = arrayFolder[i];
			if (arrayFolder[i].isDirectory()) {
				String name = info.getName();
				if (name.equals(".svn") || StringUtils.isEmpty(name) || name.equals("tpl")) continue;
				listFolders.add(name);
			}
		}

		return listFolders;
	}
}
