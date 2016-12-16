package egovmei.apps.modules.dashboard.dao;

import egovmei.apps.modules.dashboard.domain.Dashboard;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
public interface DashboardDAO {
	List<Dashboard> findAll();
	Dashboard findOne(String dashboardIdx);
	void save(Dashboard dashboard);
}
