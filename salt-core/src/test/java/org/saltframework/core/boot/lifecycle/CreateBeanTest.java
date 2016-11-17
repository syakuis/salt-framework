package org.saltframework.core.boot.lifecycle;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 17.
 */
public class CreateBeanTest implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		System.out.println("========== CreateBeanTest.getObject ====> object");
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
