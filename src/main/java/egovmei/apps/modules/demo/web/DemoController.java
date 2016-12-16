package egovmei.apps.modules.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 3.
 */
@Controller
public class DemoController {

	@Autowired
	private CacheManager cacheManager;

	public String dispDemoList() {
		return null;
	}
}
