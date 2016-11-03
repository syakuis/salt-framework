package egovmei.modules.demo.dao;

import egovmei.modules.demo.domain.Demo;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
public interface DemoDAO {
	List<Demo> findAll();
	Demo findOne(String demoIdx);
	void save(Demo demo);
}
