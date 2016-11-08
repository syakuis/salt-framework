package org.saltframework.apps.modules.module.dao;

import org.saltframework.apps.modules.module.domain.Module;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
public interface ModuleDAO {
	List<Module> findAll();
	void save(Module module);
}
