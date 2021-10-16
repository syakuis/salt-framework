package org.saltframework.store;

import org.saltframework.store.model.Store;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 3.
 */
public class StoreManager {
	private Map<String, Store> storeMap;

	public StoreManager() {
		this.storeMap = new HashMap<>();
	}

	public void instance(String name, Store store) {
		storeMap.put(name, store);
	}

	public Store getStore(String name) {
		if (!storeMap.containsKey(name)) {
			return new Store();
		}
		return storeMap.get(name);
	}
}
