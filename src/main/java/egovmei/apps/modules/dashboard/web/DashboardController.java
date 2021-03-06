package egovmei.apps.modules.dashboard.web;

import egovmei.apps.modules.dashboard.domain.DashboardVO;
import egovmei.apps.modules.dashboard.service.DashboardService;
import org.saltframework.web.handler.SuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 12. 16.
 */
@Controller
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Resource(name = "dashboardEgovService")
	DashboardService dashboardService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public SuccessHandler procDashboardList() {
		SuccessHandler<String> successHandler = new SuccessHandler<>();
		successHandler.setData("s");
		return successHandler;
	}

	@RequestMapping(value = "/dashboard/save", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public SuccessHandler procDashboardSave(@RequestBody DashboardVO dashboardVO) {
		SuccessHandler<Void> successHandler = new SuccessHandler<>();
		dashboardService.saveDashboard(dashboardVO);
		return successHandler;
	}
}
