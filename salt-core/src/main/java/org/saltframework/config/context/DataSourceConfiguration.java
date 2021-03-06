package org.saltframework.config.context;

import org.apache.commons.dbcp2.BasicDataSource;
import org.saltframework.util.object.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 9. 7.
 */
@Configuration
public class DataSourceConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

	private final static String propertiesName = "dataSource.";

	@Autowired
	private Properties config;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws NamingException {
		BasicDataSource dataSource = new BasicDataSource();

		String driverClassName = config.getProperty("dataSource.driverClassName");
		String url = config.getProperty("dataSource.url");
		String username = config.getProperty("dataSource.username");
		String password = config.getProperty("dataSource.password");

		Integer initialSize = (Integer) config.get("dataSource.initialSize");

		Integer maxTotal = (Integer) config.get("dataSource.maxTotal");
		Integer maxIdle = (Integer) config.get("dataSource.maxIdle");
		Integer minIdle = (Integer) config.get("dataSource.minIdle");
		Integer maxWaitMills = (Integer) config.get("dataSource.maxWaitMills");
		Boolean testOnBorrow = (Boolean) config.get("dataSource.testOnBorrow");
		Boolean testOnReturn = (Boolean) config.get("dataSource.testOnReturn");
		Long timeBetweenEvictionRunsMillis = (Long) config.get("dataSource.timeBetweenEvictionRunsMillis");
		Integer numTestsPerEvictionRun = (Integer) config.get("dataSource.numTestsPerEvictionRun");
		Long minEvictableIdleTimeMillis = (Long) config.get("dataSource.minEvictableIdleTimeMillis");
		Boolean testWhileIdle = (Boolean) config.get("dataSource.testWhileIdle");

		String validationQuery = config.getProperty("dataSource.validationQuery");
		Integer validationQueryTimeout = (Integer) config.get("dataSource.validationQueryTimeout");
		String connectionInitSqls = config.getProperty("dataSource.connectionInitSqls");
		Boolean defaultReadOnly = (Boolean) config.get("dataSource.defaultReadOnly");
		Boolean defaultAutoCommit = (Boolean) config.get("dataSource.defaultAutoCommit");
		Integer defaultTransactionIsolation = (Integer) config.get("dataSource.defaultTransactionIsolation");
		String defaultCatalog = config.getProperty("dataSource.defaultCatalog");

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		if (initialSize != null) {
			dataSource.setInitialSize(initialSize.intValue());
		}

		if (maxTotal != null) {
			dataSource.setMaxTotal(maxTotal);
		}

		if (maxIdle != null) {
			dataSource.setMinIdle(maxIdle.intValue());
		}

		if (minIdle != null) {
			dataSource.setMinIdle(minIdle.intValue());
		}

		if (maxWaitMills != null) {
			dataSource.setMaxWaitMillis(maxWaitMills.intValue());
		}

		if (testOnBorrow != null) {
			dataSource.setTestOnBorrow(testOnBorrow.booleanValue());
		}

		if (testOnReturn != null) {
			dataSource.setTestOnReturn(testOnReturn.booleanValue());
		}

		if (timeBetweenEvictionRunsMillis != null) {
			dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis.longValue());
		}

		if (numTestsPerEvictionRun != null) {
			dataSource.setNumTestsPerEvictionRun(numTestsPerEvictionRun.intValue());
		}

		if (minEvictableIdleTimeMillis != null) {
			dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis.longValue());
		}

		if (testWhileIdle != null) {
			dataSource.setTestWhileIdle(testWhileIdle.booleanValue());
		}

		if (validationQuery != null) {
			dataSource.setValidationQuery(validationQuery);
		}
		if (validationQueryTimeout != null) {
			dataSource.setValidationQueryTimeout(validationQueryTimeout.intValue());
		}
		if (connectionInitSqls != null) {
			String[] connectionInitSqlsArray = StringUtils.tokenizeToStringArray(connectionInitSqls, ",");
			dataSource.setConnectionInitSqls(Arrays.asList(connectionInitSqlsArray));
		}
		if (defaultReadOnly != null) {
			dataSource.setDefaultReadOnly(defaultReadOnly);
		}
		if (defaultAutoCommit != null) {
			dataSource.setDefaultAutoCommit(defaultAutoCommit);
		}
		if (defaultTransactionIsolation != null) {
			dataSource.setDefaultTransactionIsolation(defaultTransactionIsolation.intValue());
		}
		if (defaultCatalog != null) {
			dataSource.setDefaultCatalog(defaultCatalog);
		}

		if (logger.isDebugEnabled()) {
			List<String> names = PropertiesUtils.getNames(config, propertiesName);
			for (String name : names) {
				logger.debug("{} : {}", name, config.getProperty(name));
			}
		}

		return dataSource;
	}
}
