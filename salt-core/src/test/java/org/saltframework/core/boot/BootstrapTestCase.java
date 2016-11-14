package org.saltframework.core.boot;

import org.junit.runner.RunWith;
import org.saltframework.config.BootstrapConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootstrapConfiguration.class)
public class BootstrapTestCase {
}
