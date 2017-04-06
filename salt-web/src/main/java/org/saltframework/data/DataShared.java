package org.saltframework.data;

import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 6.
 */
public class DataShared<T> {
	@Getter
	private final T data;

	public DataShared(T data) {
		this.data = data;
	}
}
