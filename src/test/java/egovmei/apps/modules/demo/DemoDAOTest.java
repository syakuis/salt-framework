package egovmei.apps.modules.demo;

import egovmei.config.ConfigContext;
import egovmei.apps.modules.demo.dao.DemoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		BootstrapConfiguration.class,
		ConfigContext.class
})
public class DemoDAOTest {

	@Resource(name = "demoEgovMapper")
	private DemoDAO demoDAO;

	@Test
	public void test() {
		demoDAO.findAll();
	}

}
