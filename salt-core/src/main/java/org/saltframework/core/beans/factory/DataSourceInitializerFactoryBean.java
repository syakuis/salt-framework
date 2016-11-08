package org.saltframework.core.beans.factory;

import org.saltframework.util.io.PathMatchingResourceResolver;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 8. 10.
 */
public class DataSourceInitializerFactoryBean extends AbstractFactoryBean<DataSourceInitializer> {
	private final DataSource dataSource;
	private String schemeTables;
	private String schemeDatas;
	private ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();

	public DataSourceInitializerFactoryBean(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setSqlScriptEncoding(String sqlScriptEncoding) {
		databasePopulator.setSqlScriptEncoding(sqlScriptEncoding);
	}

	public void setSeparator(String separator) {
		databasePopulator.setSeparator(separator);
	}

	public void setCommentPrefix(String commentPrefix) {
		databasePopulator.setCommentPrefix(commentPrefix);
	}

	public void setBlockCommentStartDelimiter(String blockCommentStartDelimiter) {
		databasePopulator.setBlockCommentStartDelimiter(blockCommentStartDelimiter);
	}

	public void setBlockCommentEndDelimiter(String blockCommentEndDelimiter) {
		databasePopulator.setBlockCommentEndDelimiter(blockCommentEndDelimiter);
	}

	public void setContinueOnError(boolean continueOnError) {
		databasePopulator.setContinueOnError(continueOnError);
	}

	public void setIgnoreFailedDrops(boolean ignoreFailedDrops) {
		databasePopulator.setIgnoreFailedDrops(ignoreFailedDrops);
	}

	public void setSchemeTables(String schemeTables) {
		this.schemeTables = schemeTables;
	}

	public void setSchemeDatas(String schemeDatas) {
		this.schemeDatas = schemeDatas;
	}

	@Override
	public Class<DataSourceInitializer> getObjectType() {
		return DataSourceInitializer.class;
	}

	@Override
	protected DataSourceInitializer createInstance() throws Exception {
		DataSourceInitializer initializer = new DataSourceInitializer();

		PathMatchingResourceResolver pathResourcePatternResolver = new PathMatchingResourceResolver();
		Resource[] schemeTableResources = pathResourcePatternResolver.getResources(StringUtils.tokenizeToStringArray(schemeTables, ","));
		Resource[] schemeDataResources = pathResourcePatternResolver.getResources(StringUtils.tokenizeToStringArray(schemeDatas, ","));

		for(Resource resource : schemeTableResources) {
			databasePopulator.addScript(resource);
		}

		for(Resource resource : schemeDataResources) {
			databasePopulator.addScript(resource);
		}

		databasePopulator.setIgnoreFailedDrops(true);

		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator);

		return null;
	}
}
