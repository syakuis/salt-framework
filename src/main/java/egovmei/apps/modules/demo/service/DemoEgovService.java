package egovmei.apps.modules.demo.service;

import egovmei.apps.modules.demo.dao.DemoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Service
public class DemoEgovService implements DemoService {

	@Resource(name = "demoEgovMapper")
	private DemoDAO demoDAO;
}