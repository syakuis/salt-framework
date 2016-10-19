package org.saltframework.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootstrapContext.class)
public class BootTest {

	@Test
	public void test() {

	}
}
