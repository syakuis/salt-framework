package org.saltframework.beans.factory;


import org.saltframework.util.object.PropertiesTool;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 22.
 */
public class JpaEntityManagerFactoryBean extends AbstractFactoryBean<LocalContainerEntityManagerFactoryBean> {
	private final DataSource dataSource;
	private Properties properties;
	private Properties jpaProperties;
	private String[] packagesToScan;
	private String packageToScan;
	private JpaVendorAdapter vendorAdapter;

	public JpaEntityManagerFactoryBean(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setJpaProperties(Properties jpaProperties) {
		this.jpaProperties = jpaProperties;
	}

	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan.clone();
	}

	public void setPackageToScan(String packageToScan) {
		this.packageToScan = packageToScan;
	}

	public void setVendorAdapter(JpaVendorAdapter vendorAdapter) {
		this.vendorAdapter = vendorAdapter;
	}

	@Override
	protected LocalContainerEntityManagerFactoryBean createInstance() {
		LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();

		if (!"".equals(packageToScan)) {
			packagesToScan = StringUtils.tokenizeToStringArray(packageToScan, ",");
		}

		if (jpaProperties == null && properties != null) {
			jpaProperties = new Properties();

			for(String name : PropertiesTool.getNames(properties, "jpa.hibernate")) {
				jpaProperties.setProperty(name.replace("jpa.", ""), properties.getProperty(name));
			}
		}


		manager.setDataSource(dataSource);
		manager.setPackagesToScan(packagesToScan);

		if (vendorAdapter == null) {
			vendorAdapter = new HibernateJpaVendorAdapter();
		}
		manager.setJpaVendorAdapter(vendorAdapter);
		manager.setJpaProperties(properties);

		return manager;
	}

	@Override
	public Class<LocalContainerEntityManagerFactoryBean> getObjectType() {
		return LocalContainerEntityManagerFactoryBean.class;
	}
}
