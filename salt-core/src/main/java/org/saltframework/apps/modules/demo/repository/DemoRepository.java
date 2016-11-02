package org.saltframework.apps.modules.demo.repository;

import org.saltframework.apps.modules.demo.dao.DemoDAO;
import org.springframework.stereotype.Repository;

/**
 * jpa dao
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 2.
 */
@Repository("demoDAO")
public interface DemoRepository extends DemoDAO {
}
