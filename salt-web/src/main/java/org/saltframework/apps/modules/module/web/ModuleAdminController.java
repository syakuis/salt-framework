package org.saltframework.apps.modules.module.web;

import org.saltframework.apps.modules.module.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
		ModelAndView mav = new ModelAndView("modules/module/tpl/admin.module.list");
		mav.addObject("modules", moduleService.getModuleList());
		return mav;
	}
}
