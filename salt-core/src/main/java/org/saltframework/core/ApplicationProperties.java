package org.saltframework.core;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 15.
 */
public final class ApplicationProperties {

	private Map<String, List<Properties>> context = new LinkedHashMap<>();

	protected void addProperties(ApplicationType applicationType, List<Properties> properties) {
		this.context.put(applicationType.name(), properties);
	}

	public List<Properties> getProperties(ApplicationType applicationType) {
		if (context.isEmpty() || !context.containsKey(applicationType.name())) {
			return Collections.EMPTY_LIST;
		} else {
			return context.get(applicationType.name());
		}
	}
}
