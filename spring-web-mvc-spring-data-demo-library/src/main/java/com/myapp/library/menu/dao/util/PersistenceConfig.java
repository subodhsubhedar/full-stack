package com.myapp.library.menu.dao.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;

/**
 * 
 * @author Admin
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.myapp.library.menu.repository")

public class PersistenceConfig {

	
}
