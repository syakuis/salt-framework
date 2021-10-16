package org.saltframework.beans.factory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 2. 26.
 */
public final class BootstrapIntro {
	private static final Logger logger = LoggerFactory.getLogger(BootstrapIntro.class);

	private BootstrapIntro() {
		super();
	}


	/**
	 * http://www.network-science.de/ascii/
	 * standard type smslant slant
	 */
	public static void print(Properties properties) {
		StringBuilder print = new StringBuilder();

		print.append("\n_________________________________________________________________________\n");
		print.append("                                                                         \n");
		print.append("   ____       __ __   ____                                          __\n");
		print.append("  / __/___ _ / // /_ / __/____ ___ _ __ _  ___  _    __ ___   ____ / /__\n");
		print.append(" _\\ \\ / _ `// // __// _/ / __// _ `//  ' \\/ -_)| |/|/ // _ \\ / __//  '_/\n");
		print.append("/___/ \\_,_//_/ \\__//_/  /_/   \\_,_//_/_/_/\\__/ |__,__/ \\___//_/  /_/\\_\\\n");
		print.append("                                                  version " + properties.getProperty("config.version"));
		print.append("\n                                                                         \n");
		print.append("                                                                         \n");
		print.append("                           Salt Framework by 52572 49437 44512   \n");
		print.append("                                                                         \n");
		print.append("_________________________________________________________________________\n\n");
		print.append("* @timeZone: " + properties.getProperty("config.timeZone"));
		print.append("\n");
		print.append("* @date: " + new Date());
		print.append("\n");
		print.append("* @java encoding: " + properties.getProperty("config.charset"));
		print.append("\n");
		print.append("* @profile: " + properties.getProperty("config.profile"));
		print.append("\n");
		print.append("* @profiles: " + properties.getProperty("config.profiles"));
		print.append("\n");
		print.append("_________________________________________________________________________\n\n");

		logger.warn(print.toString());

		if (logger.isDebugEnabled()) {
			Iterator<String> iterator = properties.stringPropertyNames().iterator();

			while (iterator.hasNext()) {
				String name = iterator.next();

				logger.debug("{} : {}", name, properties.getProperty(name));
			}
		}
	}
}
