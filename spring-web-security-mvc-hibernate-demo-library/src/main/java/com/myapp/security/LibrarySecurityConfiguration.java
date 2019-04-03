package com.myapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LibrarySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService libraryUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder  passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Encoding password
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		/*auth.inMemoryAuthentication()
			.withUser("David")
				.password(encoder.encode("david123"))
				.roles("PRINCIPAL")
			.and()
				.withUser("Jyoti")
				.password(encoder.encode("jyoti123"))
				.roles("LIBRARIAN")
			.and()
				.withUser("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN");*/
		
		/*auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery
			("select user_id, password, username from library_users where username=?")
		.authoritiesByUsernameQuery
			("select lb.role_id, lb.role_name from library_roles lb left join library_users lu on lb.role_id = lu.roles_role_id where lu.username=?");
			*/
		auth.userDetailsService(libraryUserDetailsService).passwordEncoder(passwordEncoder);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests().antMatchers("/login").permitAll()
			.and()			
				.formLogin().loginPage("/login")
				.usernameParameter("username").passwordParameter("password").permitAll()
	            .successForwardUrl("/processLogin")
	            .failureUrl("/login?error=true")
	        .and()
	            .logout().logoutUrl("/logout").permitAll()
			.and()
				.authorizeRequests().antMatchers("/welcome").hasAnyRole("PRINCIPAL", "ADMIN","LIBRARIAN")

	        .and()
				.authorizeRequests().antMatchers("/menu/add-new-subject").hasAnyRole("PRINCIPAL", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/delete-subject").hasAnyRole("PRINCIPAL", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/search-subject").hasAnyRole("PRINCIPAL", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/all-subjects").hasAnyRole("PRINCIPAL", "ADMIN")
			
			.and()
				.authorizeRequests().antMatchers("/menu/add-new-book").hasAnyRole("LIBRARIAN", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/delete-book").hasAnyRole("LIBRARIAN", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/search-book").hasAnyRole("LIBRARIAN", "ADMIN")
			.and()
				.authorizeRequests().antMatchers("/menu/all-books").hasAnyRole("LIBRARIAN", "ADMIN")
		
			.and()
				.exceptionHandling().accessDeniedPage("/WEB-INF/accessDenied.jsp")
			.and()
				.csrf().disable();
	}

}
