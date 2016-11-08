package org.saltframework.apps.modules.module.service;

import org.saltframework.apps.modules.module.dao.ModuleDAO;
import org.saltframework.apps.modules.module.domain.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
@Service
public class ModuleService {
	@Resource(name = "moduleDAO")
	private ModuleDAO moduleDAO;

	public List<Module> getModuleList() {
		return moduleDAO.findAll();
	}

	public void save(Module module) {
		moduleDAO.save(module);
	}
}
