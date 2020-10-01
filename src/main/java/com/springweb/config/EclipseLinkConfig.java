package com.springweb.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableJpaRepositories("com.springweb.daoImpl")
public class EclipseLinkConfig extends JpaBaseConfiguration {


	

	protected EclipseLinkConfig(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
		super(dataSource, properties, jtaTransactionManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

	@Override
	protected Map<String, Object> getVendorProperties() {
		final Map<String, Object> ret = new HashMap<>();
		ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
		return ret;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "com.springweb.daoImpl" });
		em.setPersistenceUnitName("ESM_JPA");
		JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;

		/*
		 * return builder .dataSource(dataSource) .packages("com.springweb.daoImpl")
		 * .persistenceUnit("ESM_JPA") .properties(initJpaProperties()).build();
		 */
	}

	@Bean
	public static DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:ORCL12C");
		dataSource.setUsername("sample_db");
		dataSource.setPassword("sample_db");*/
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://192.168.151.63:5432/SpringWeb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("Password1");
		return dataSource;
	}

	@Bean
	public static PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	@Primary
	public static JpaProperties properties() {
		final JpaProperties jpaProperties = new JpaProperties();
		jpaProperties.setShowSql(true);
		// jpaProperties.setDatabasePlatform("org.eclipse.persistence.platform.database.H2Platform");
		return jpaProperties;
	}

	private static Map<String, ?> initJpaProperties() {
		final Map<String, Object> ret = new HashMap<>();
		// Add any JpaProperty you are interested in and is supported by your Database
		// and JPA implementation
		ret.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
		ret.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINEST_LABEL);
		ret.put(PersistenceUnitProperties.WEAVING, "false");
		ret.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_ONLY);
		ret.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
		return ret;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("eclipselink.ddl-generation", "create-or-extend-tables");
		properties.setProperty("eclipselink.ddl-generation.output-mode", "sql-script");
		properties.setProperty("eclipselink.create-ddl-jdbc-file-name", "createDDL_ddlGeneration.jdbc");
		properties.setProperty("eclipselink.drop-ddl-jdbc-file-name", "dropDDL_ddlGeneration.jdbc");
		properties.setProperty("eclipselink.weaving", "static");
		properties.setProperty("eclipselink.logging.level", "FINE");
		properties.setProperty("eclipselink.logging.exception", "true");
		return properties;
	}

}
