package org.saltframework.util.object;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 31.
 */
public final class PropertiesUtils {
	private PropertiesUtils() {
	}

	/**
	 * 매개변수 target과 일치하는 프로퍼티ㄴ를 찾는 다.
	 *
	 * @param properties the properties
	 * @param target     the target
	 * @return the names
	 */
	public static List<String> getNames(Properties properties, String target) {
		Set<String> propertiesName = properties.stringPropertyNames();

		List<String> names = new ArrayList<>();

		Iterator<String> iterator = propertiesName.iterator();
		while(iterator.hasNext()) {
			String name = iterator.next();
			if (name != null && name.startsWith(target)) {
				names.add(name);
			}
		}

		return names;
	}
}
