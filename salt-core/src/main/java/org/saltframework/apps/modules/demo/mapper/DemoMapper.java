package org.saltframework.apps.modules.demo.mapper;

import org.saltframework.apps.modules.demo.dao.DemoDAO;
import org.saltframework.support.mybatis.Mapper;

/**
 * mybatis dao
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Mapper("demoDAO")
public interface DemoMapper extends DemoDAO {
}
