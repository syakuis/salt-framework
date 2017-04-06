package org.saltframework.data;

import org.springframework.cache.Cache;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 4. 7.
 */
public class DataSharedCacheManager {
	private Cache cache;
	public DataSharedCacheManager(Cache cache) {
		this.cache = cache;
	}

	public DataShared get(String id) {
		return cache.get(id, DataShared.class);
	}

	public void put(String id, DataShared data) {
		cache.put(id, data);
	}

	public void remove(String id) {
		cache.evict(id);
	}

	public void clean() {
		cache.clear();
	}
}
