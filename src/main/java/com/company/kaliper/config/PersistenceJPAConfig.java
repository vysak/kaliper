package com.company.kaliper.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:database.properties","classpath:hibernate.properties"})
public class PersistenceJPAConfig {
	private static final String PROPERTY_NAME_DATABASE_DRIVER   = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL      = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_PACKAGES_TO_SCAN = "packages.to.scan";
    
    @Resource
    private Environment env;
    
    private String get(String key){
		return env.getRequiredProperty(key);
	}
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(get(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(get(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(get(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(get(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
  
       return transactionManager;
    }
  
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource());
       em.setPackagesToScan(new String[] { env.getRequiredProperty(PROPERTY_NAME_PACKAGES_TO_SCAN) });
  
       HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalProperties());
       return em;
    }
    
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.setProperty(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        return properties;
    }

}
