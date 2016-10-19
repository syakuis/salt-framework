package org.saltframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 4. 21.
 */

@Configuration
@ComponentScan(
		basePackages = "org.saltframework.config.context",
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		}
)
public class BootstrapContext {

	@Autowired Properties salt;

	@PostConstruct
	public void init() {
		System.out.println("__________________________________________________________");
		System.out.println("                                                          ");
		System.out.println(" ___       _ _   ___                                  _   ");
		System.out.println("/ __| __ _| | |_| __| _ __ _ _ __  _____ __ _____ _ _| |__");
		System.out.println("\\__ \\/ _` | |  _| _| '_/ _` | '  \\/ -_) V  V / _ \\ '_| / /");
		System.out.println("|___/\\__,_|_|\\__|_||_| \\__,_|_|_|_\\___|\\_/\\_/\\___/_| |_\\_\\");
		System.out.println("                                     version " + salt.getProperty("salt.version"));
		System.out.println("                                                          ");
		System.out.println("                    Salt Framework by 52572 49437 44512   ");
		System.out.println("__________________________________________________________");
		System.out.println("");
		System.out.println("* @timeZone: " + salt.getProperty("salt.timeZone"));
		System.out.println("* @date: " + new Date());
		System.out.println("* @java encoding: " + salt.getProperty("salt.charset"));
		System.out.println("* @profile: " + salt.getProperty("salt.profile"));
		System.out.println("* @profiles: " + salt.getProperty("salt.profiles"));
		System.out.println("__________________________________________________________");
	}

}
