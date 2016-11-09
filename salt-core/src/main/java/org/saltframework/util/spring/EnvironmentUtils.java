package org.saltframework.util.spring;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.env.Environment;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 9.
 */
public final class EnvironmentUtils {
	private EnvironmentUtils() {

	}

	public static String[] getProfiles(Environment env) {
		return ArrayUtils.addAll(env.getDefaultProfiles(), env.getActiveProfiles());
	}
}
