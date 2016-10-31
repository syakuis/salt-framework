package org.saltframework.config.context;

import org.apache.commons.dbcp.BasicDataSource;
import org.saltframework.util.object.PropertiesTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 7.
 */
@Configuration
public class DataSourceContext {
	private final String propertiesName = "dataSource.";

	@Autowired
	private Properties config;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		List<String> names = PropertiesTool.getNames(config, propertiesName);
		for(String name : names) {
			String value = config.getProperty(name);
			if (!"".equals(value)) {
				name = name.replaceAll(propertiesName, "");
				dataSource.addConnectionProperty(name, value);
			}
		}

		return dataSource;
	}
}
