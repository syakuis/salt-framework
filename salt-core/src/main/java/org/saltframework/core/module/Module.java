package org.saltframework.core.module;

import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2016. 11. 2.
 */
public interface Module {
	/**
	 * Gets group id.
	 *
	 * @return the group id
	 */
	String getGroupId();

	/**
	 * Gets module id.
	 *
	 * @return the module id
	 */
	String getModuleId();

	/**
	 * Gets module name.
	 *
	 * @return the module name
	 */
	String getModuleName();

	/**
	 * Gets skin.
	 *
	 * @return the skin
	 */
	String getSkin();

	/**
	 * Gets layoutId
	 *
	 * @return the layout
	 */
	String getLayoutId();

	/**
	 * Is parent boolean.
	 *
	 * @return the boolean
	 */
	boolean isParent();

	/**
	 * Gets option.
	 *
	 * @param name the name
	 * @return the option
	 */
	Object getOption(String name);

	/**
	 * 모듈과 오션을 Map 형식으로 리턴한다.
	 *
	 * @return the options
	 */
	Map<String, Object> toMap();

	/**
	 * 모듈 옵션을 List 형식으로 리턴한다.
	 *
	 * @return the list
	 */
	List<Option> options();
}
