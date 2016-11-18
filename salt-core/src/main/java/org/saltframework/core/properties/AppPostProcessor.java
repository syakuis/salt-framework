package org.saltframework.core.properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public class AppPostProcessor implements ApplicationPostProcessor {

	@Override
	public ApplicationType getType() {
		return ApplicationType.APP;
	}

	@Override
	public String getSearchKey() {
		return "app.";
	}
}
