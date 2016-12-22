package egovmei.apps.modules.dashboard.web;

import egovmei.apps.modules.dashboard.domain.Dashboard;
import egovmei.apps.modules.dashboard.service.DashboardService;
import org.saltframework.web.handler.SuccessHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 12. 16.
 */
@Controller
public class DashboardController {

	@Resource(name = "dashboardEgovService")
	DashboardService dashboardService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dispModuleAdminList() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("template", "/modules/dashboard/dashboard.save.ftl");
		return mav;
	}

	@RequestMapping(value = "/dashboard/list", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public SuccessHandler procDashboardList() {
		SuccessHandler<String> successHandler = new SuccessHandler<>();
		successHandler.setData("s");
		return successHandler;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public SuccessHandler procDashboardSave(@RequestBody Dashboard dashboard) {
		SuccessHandler<Void> successHandler = new SuccessHandler<>();
		dashboardService.save(dashboard);
		return successHandler;
	}
}
