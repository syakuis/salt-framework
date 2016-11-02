package org.saltframework.core.module;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
public final class Options {
	private final Map<String, Object> option;

	public Options(Map<String, Object> option) {
		this.option = option;
	}

	public Object getValue(String name) {
		return this.option.get(name);
	}
}
