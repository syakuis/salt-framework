package org.saltframework.apps.modules.demo.service;

import org.saltframework.apps.modules.demo.dao.DemoDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 1.
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Resource(name = "demoDAO")
	private DemoDAO demoDAO;
}