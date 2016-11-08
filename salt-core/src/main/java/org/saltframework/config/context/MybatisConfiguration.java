package org.saltframework.config.context;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.saltframework.support.mybatis.Mapper;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 31.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "org.saltframework.apps", annotationClass = Mapper.class)
public class MybatisConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	@Autowired
	private Properties config;

	@Autowired
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		String type = config.getProperty("dataSource.type");
		Assert.hasText(type);

		String configLocation = config.getProperty("mybatis.configLocation");
		String[] mapperLocations = StringUtils.tokenizeToStringArray(config.getProperty("config.mybatis.mapperLocations"), ",");
		String[] mapperLocations2 = StringUtils.tokenizeToStringArray(config.getProperty("mybatis.mapperLocations"), ",");
		if (mapperLocations2.length > 0) {
			mapperLocations = ArrayUtils.addAll(mapperLocations, mapperLocations2);
		}

		String[] mappers = new String[ mapperLocations.length ];
		for (int i = 0; i < mapperLocations.length; i++) {
			mappers[i] = String.format(mapperLocations[i], type);
		}

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] resources = pathResourcePatternResolver.getResources(mappers);
		sqlSessionFactoryBean.setMapperLocations(resources);

		if (!"".equals(configLocation)) {
			sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("\ndbms : {}\nconfigLocation : {}\nmapperLocations : {}",
					type,
					configLocation,
					StringUtils.arrayToCommaDelimitedString(mappers));
		}

		return sqlSessionFactoryBean.getObject();
	}
}
