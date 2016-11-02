package org.saltframework.config.context;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.saltframework.support.mybatis.Mapper;
import org.saltframework.util.io.PathMatchingResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 10. 31.
 */
@Configuration
@Profile("mybatis")
@EnableTransactionManagement
@MapperScan(basePackages = "org.saltframework.apps", annotationClass = Mapper.class)
public class MybatisContext {
	@Autowired
	private Properties config;

	@Autowired
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		String dbms = config.getProperty("dataSource.dbms", "oracle");
		String configLocation = config.getProperty("mybatis.configLocation");
		String[] mapperLocations = StringUtils.tokenizeToStringArray(config.getProperty("config.mybatis.mapperLocations"), ",");
		String[] mapperLocations2 = StringUtils.tokenizeToStringArray(config.getProperty("mybatis.mapperLocations"), ",");
		if (mapperLocations2.length > 0) {
			mapperLocations = ArrayUtils.addAll(mapperLocations, mapperLocations2);
		}

		String[] mappers = new String[ mapperLocations.length ];
		for (int i = 0; i < mapperLocations.length; i++) {
			mappers[i] = String.format(mapperLocations[i], dbms);
		}

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] resources = pathResourcePatternResolver.getResources(mappers);
		sqlSessionFactoryBean.setMapperLocations(resources);

		if (!"".equals(configLocation)) {
			sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
		}

		return sqlSessionFactoryBean.getObject();
	}
}
