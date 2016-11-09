package org.saltframework.apps.modules.module.web;

import org.saltframework.apps.modules.module.domain.Module;
import org.saltframework.apps.modules.module.domain.ModuleOption;
import org.saltframework.apps.modules.module.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
@Controller
@RequestMapping("/admin/module")
public class ModuleAdminController {
	@Resource(name = "moduleService") private ModuleService moduleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView dispModuleAdminList(Model model) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("template", "/modules/module/admin.module.list.ftl");
		mav.addObject("modules", moduleService.getModuleList());
		return mav;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView dispModuleAdminInsert() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("template", "/modules/module/admin.module.save.ftl");


		// module_idx 로 조회된 모듈 정보를 담는다.
		mav.addObject("module", new Module()); // 모듈 정보를 담는 다.
		mav.addObject("moduleOptions", new ArrayList<ModuleOption>()); // 모듈 옵션정보를 분리하기 위해 담는 다.

		mav.addObject("listLayout", new ArrayList<String>());
		mav.addObject("listSkinsFolder", new ArrayList<String>());
		mav.addObject("listModulesFolder", new ArrayList<String>());

		return mav;
	}
}
