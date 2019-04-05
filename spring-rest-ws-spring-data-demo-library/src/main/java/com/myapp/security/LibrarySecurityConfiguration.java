package com.myapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class LibrarySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService libraryUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Encoding password
		auth.userDetailsService(libraryUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public BasicAuthenticationEntryPoint getLibraryBasicAuthPoint() {
		return new LibraryBasicAuthEntryPoint();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().authenticationEntryPoint(getLibraryBasicAuthPoint()).realmName("SPRING-REST-SECURITY").and()
				.authorizeRequests().antMatchers(HttpMethod.GET, "/subjects").hasAnyRole("PRINCIPAL", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.GET, "/books").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.GET, "/book/**").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.POST, "/book/add").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.PUT, "/book/update").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.DELETE, "/book/delete/**").hasAnyRole("LIBRARIAN", "ADMIN")
				.and().csrf().disable().formLogin().disable();
	}

}
