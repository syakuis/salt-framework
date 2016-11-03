package org.saltframework.core.module;

import java.util.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public interface Module {

	String getModuleId();

	String getModuleName();

	String getModuleIdx();

	Object getOption(String name);

	Map<String, Object> getOptions();

	boolean empty();

	boolean exists(String name);

	List<Option> options();
}
