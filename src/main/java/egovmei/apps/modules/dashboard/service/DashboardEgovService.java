package egovmei.apps.modules.dashboard.service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovmei.apps.modules.dashboard.dao.DashboardDAO;
import egovmei.apps.modules.dashboard.domain.Dashboard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Service
public class DashboardEgovService extends EgovAbstractServiceImpl implements DashboardService {

	@Resource(name = "dashboardEgovMapper")
	private DashboardDAO dashboardDAO;

	@Override
	public void save(Dashboard dashboard) {
		dashboardDAO.save(dashboard);
	}
}