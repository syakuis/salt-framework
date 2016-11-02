package org.saltframework.core.module;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public final class ModuleOption {
	private final Map<String, Object> options;

	public ModuleOption(Map<String, Object> options) {
		this.options = options;
	}

	public Object getValue(String name) {
		return options.get(name);
	}
}
