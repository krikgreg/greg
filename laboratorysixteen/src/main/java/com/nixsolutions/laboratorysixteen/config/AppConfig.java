package com.nixsolutions.laboratorysixteen.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:db-hibernate.properties" })
@ComponentScan({ "com.nixsolutions.laboratorysixteen" })
public class AppConfig {

	@Bean
	public DataSource getDataSource(Environment env) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("h2.driverClass"));
		dataSource.setUrl(env.getProperty("h2.url"));
		dataSource.setUsername(env.getProperty("h2.username"));
		dataSource.setPassword(env.getProperty("h2.password"));
		return dataSource;
	}
	
	@Bean
	   public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		 sessionFactory.setPackagesToScan(
			        new String[] { "com.nixsolutions.laboratorysixteen" });
	      sessionFactory.setHibernateProperties(hibernateProperties(env));
	      return sessionFactory;
	   }
	
	@Bean
	   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(sessionFactory);
	      return transactionManager;
	   }
	
	private Properties hibernateProperties(Environment env) {
		return new Properties() {
			private static final long serialVersionUID = 1333974527706361940L;
			{
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
	}
}