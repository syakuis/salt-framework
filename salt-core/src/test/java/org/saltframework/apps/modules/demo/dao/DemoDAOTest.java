package org.saltframework.apps.modules.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootstrapContext.class)
@ActiveProfiles("mybatis")
public class DemoDAOTest {

	@Resource(name = "demoMapper")
	private DemoDAO demoDAO;

	@Test
	public void test() {
		demoDAO.findAll();
	}

}
