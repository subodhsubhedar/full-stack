package com.myapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class LibrarySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService libraryUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Component
	public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {

			response.sendRedirect("login?auth_error=" + exception.getMessage());
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Encoding password
		auth.userDetailsService(libraryUserDetailsService).passwordEncoder(passwordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll().and().formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
				.successForwardUrl("/processLogin").failureUrl("/login?error=true")
				.failureHandler(authenticationFailureHandler).and().logout().logoutUrl("/logout").permitAll()
				.invalidateHttpSession(true).and().authorizeRequests().antMatchers("/welcome")
				.hasAnyRole("PRINCIPAL", "ADMIN", "LIBRARIAN")

				.and().authorizeRequests().antMatchers("/menu/add-new-subject").hasAnyRole("PRINCIPAL", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/delete-subject").hasAnyRole("PRINCIPAL", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/search-subject").hasAnyRole("PRINCIPAL", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/all-subjects").hasAnyRole("PRINCIPAL", "ADMIN")

				.and().authorizeRequests().antMatchers("/menu/add-new-book").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/delete-book").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/search-book").hasAnyRole("LIBRARIAN", "ADMIN").and()
				.authorizeRequests().antMatchers("/menu/all-books").hasAnyRole("LIBRARIAN", "ADMIN")

				.and().exceptionHandling().accessDeniedPage("/WEB-INF/accessDenied.jsp").and().csrf().disable();
	}

}
