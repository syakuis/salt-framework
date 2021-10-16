package org.saltframework.store.model;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 3.
 */
public final class StoreBuilder {

	private StoreBuilder() {
		super();
	}

	public static<K, V> Store<K, V> create(Map<K, V> data) {
		return new Store<>(data);
	}
}
