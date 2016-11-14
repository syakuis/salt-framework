package org.saltframework.config;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 10.
 */
public enum PathConfigure {
	MODULE_SKINS_PATH_FORMAT("/WEB-INF/views/modules/%s");

	private String path;

	PathConfigure(String path) {
		this.path = path;
	}

	public String getPath() {
		return String.format(path);
	}

	public String getPath(String module) {
		return String.format(path, module);
	}
}
