package egovmei.apps.modules.dashboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovmei.apps.modules.dashboard.dao.DashboardDAO;
import egovmei.apps.modules.dashboard.dao.PortletDAO;
import egovmei.apps.modules.dashboard.domain.Dashboard;
import egovmei.apps.modules.dashboard.domain.DashboardVO;
import egovmei.apps.modules.dashboard.domain.Portlet;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.saltframework.core.io.Category;
import org.saltframework.core.io.FileSystem;
import org.saltframework.core.io.FileSystemSupport;
import org.saltframework.core.io.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Service
public class DashboardEgovService extends EgovAbstractServiceImpl implements DashboardService {

	@Autowired
	Properties config;

	@Resource(name = "sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;

	@Resource(name = "dashboardEgovMapper")
	private DashboardDAO dashboardDAO;

	@Resource(name = "portletEgovMapper")
	private PortletDAO portletDAO;

	@Override
	public void saveDashboard(DashboardVO dashboardVO) {

		try {
			FileSystemSupport file = new FileSystemSupport(config.getProperty("application.fileSystemRootPath"));
			file.setCategory(Category.caches);
			file.setSystemCode(SystemCode.modules);
			file.setSystemName("dashboard");
			file.setDirDate(false);

			ObjectMapper mapper = new ObjectMapper();
			FileSystem layout = file.save("layout.json", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dashboardVO.getLayout()).getBytes());
			FileSystem layouts = file.save("layouts.json", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dashboardVO.getLayouts()).getBytes());

			Dashboard dashboard = dashboardVO.getDashboard();
			dashboard.setLayoutJsonFile(layout.getFilePath());
			dashboard.setLayoutsJsonFile(layouts.getFilePath());

			dashboardDAO.save(dashboard);
			String dashboardIdx = dashboard.getDashboardIdx();

			SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);

			try {

				for (Portlet portlet : dashboardVO.getPortlets()) {
					portlet.setDashboardIdx(dashboardIdx);
					portletDAO.save(portlet);
				}

				session.flushStatements();
				session.commit();
			} finally {

				session.close();
			}

		} catch (IOException e) {

		}
	}
}