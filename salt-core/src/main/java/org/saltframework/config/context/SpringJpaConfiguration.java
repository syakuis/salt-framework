package org.saltframework.config.context;

import org.saltframework.core.beans.factory.JpaEntityManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 4. 22.
 */
@Configuration
@Profile("jpa")
@EnableTransactionManagement
@EnableJpaRepositories("org.saltframework.apps")
public class SpringJpaConfiguration {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private Properties config;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {

		JpaEntityManagerFactoryBean jpaEntityManagerFactoryBean = new JpaEntityManagerFactoryBean(dataSource);
		jpaEntityManagerFactoryBean.setProperties(config);
		jpaEntityManagerFactoryBean.setPackageToScan(config.getProperty("jpa.packagesToScan"));

		return jpaEntityManagerFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
