package org.saltframework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 4. 21.
 */

@Configuration
@ComponentScan(
		basePackages = "org.saltframework.config.context",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class BootstrapConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(BootstrapConfiguration.class);

	@Autowired
	private Properties config;

	/**
	 * http://www.network-science.de/ascii/
	 * standard type
	 */
	@PostConstruct
	public void init() {
		StringBuilder print = new StringBuilder();
		print.append("\n");
		print.append("__________________________________________________________");
		print.append("\n");
		print.append("                                                          ");
		print.append("\n");
		print.append(" ___       _ _   ___                                  _   ");
		print.append("\n");
		print.append("/ __| __ _| | |_| __| _ __ _ _ __  _____ __ _____ _ _| |__");
		print.append("\n");
		print.append("\\__ \\/ _` | |  _| _| '_/ _` | '  \\/ -_) V  V / _ \\ '_| / /");
		print.append("\n");
		print.append("|___/\\__,_|_|\\__|_||_| \\__,_|_|_|_\\___|\\_/\\_/\\___/_| |_\\_\\");
		print.append("\n");
		print.append("                                     version " + config.getProperty("config.version"));
		print.append("\n");
		print.append("                                                          ");
		print.append("\n");
		print.append("                    Salt Framework by 52572 49437 44512   ");
		print.append("\n");
		print.append("__________________________________________________________");
		print.append("\n");
		print.append("\n");
		print.append("* @timeZone: " + config.getProperty("config.timeZone"));
		print.append("\n");
		print.append("* @date: " + new Date());
		print.append("\n");
		print.append("* @java encoding: " + config.getProperty("config.charset"));
		print.append("\n");
		print.append("* @profile: " + config.getProperty("config.profile"));
		print.append("\n");
		print.append("* @profiles: " + config.getProperty("config.profiles"));
		print.append("\n");
		print.append("__________________________________________________________");
		print.append("\n");

		logger.warn(print.toString());

		if (logger.isDebugEnabled()) {
			Iterator<String> iterator = config.stringPropertyNames().iterator();

			while (iterator.hasNext()) {
				String name = iterator.next();

				logger.debug("{} : {}", name, config.getProperty(name));
			}
		}
	}

}
