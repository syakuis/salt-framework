package egovmei.apps.modules.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
	@GetMapping
	public String dispDemoView() {
		return "modules/demo/demo.view.ftl";
	}
}
