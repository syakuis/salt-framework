package org.saltframework.store.model;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 4.
 */
public class StoreMap<K, V> {
	private final K name;
	private final V value;

	public StoreMap(K name, V value) {
		this.name = name;
		this.value = value;
	}

	public K getName() {
		return name;
	}

	public V getValue() {
		return value;
	}
}
