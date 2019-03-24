package com.myapp.library.menu.dao.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;

/**
 * 
 * @author Admin
 *
 */
//@Configuration
public class HibernateConfig {

	@Value("classpath:hibernate.cfg.xml")
	private Resource hibernateConfigFile;

	//@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setConfigLocation(hibernateConfigFile);
		factoryBean.setAnnotatedClasses(Subject.class, Book.class);
		return factoryBean;
	}

	//@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

}
