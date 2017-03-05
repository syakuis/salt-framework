package org.saltframework.store;

import org.junit.Assert;
import org.junit.Test;
import org.saltframework.store.model.Store;
import org.saltframework.store.model.StoreBuilder;

import java.util.HashMap;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 3.
 */
public class StoreManagerTest {

	@Test
	public void test() {
		StoreManager storeManager = new StoreManager();

		Store store = storeManager.getStore("store");
		Assert.assertNotNull("this is null", store);
		Assert.assertTrue("this is not empty", store.isEmpty());

		storeManager.setStore("module", StoreBuilder.create(new HashMap()));

		Store module = storeManager.getStore("module");
		Assert.assertNotNull("module is null", module);
		Assert.assertTrue("module is not empty", module.isEmpty());
	}
}
