package org.saltframework.core.module;

import org.saltframework.core.properties.ApplicationPostProcessor;
import org.saltframework.core.properties.ApplicationType;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public class ModulePostProcessor implements ApplicationPostProcessor {
	@Override
	public ApplicationType getType() {
		return ApplicationType.MODULE;
	}

	@Override
	public String getSearchKey() {
		return "module.";
	}
}
