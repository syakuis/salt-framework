package org.saltframework.core.module;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public class Option implements Serializable {
	private static final long serialVersionUID = -1356192845779265736L;
	private final String name;
	private final Object value;

	public Option(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
