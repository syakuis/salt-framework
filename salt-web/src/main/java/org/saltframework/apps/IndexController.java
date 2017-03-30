package org.saltframework.apps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String dispIndexView() {
		return "index";
	}
}
