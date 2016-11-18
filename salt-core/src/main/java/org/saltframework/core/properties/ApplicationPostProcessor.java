package org.saltframework.core.properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public interface ApplicationPostProcessor {

	ApplicationType getType();
	String getSearchKey();
}
