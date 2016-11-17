package org.saltframework.core.module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 14.
 */
public interface ModuleContext {
	Module get(String name);
	void save(String name, Module module);
	Module merge(String name, Module module, boolean isNull);
}
