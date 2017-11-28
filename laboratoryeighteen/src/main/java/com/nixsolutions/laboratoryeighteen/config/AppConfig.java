package com.nixsolutions.laboratoryeighteen.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.nixsolutions.laboratoryeighteen" })
public class AppConfig {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return sessionFactoryBean;
}
	@Bean
	   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(sessionFactory);
	      return transactionManager;
	   }
}