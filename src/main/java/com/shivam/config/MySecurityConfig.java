package com.shivam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser(users.username("employee1").password("s").roles("EMPLOYEE"))
									 .withUser(users.username("manager1").password("s").roles("EMPLOYEE", "MANAGER"))
									 .withUser(users.username("admin1").password("s").roles("EMPLOYEE", "ADMIN"));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests() 						// Allow restriction based on HttpServletRequests
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/system/**").hasRole("ADMIN")
		.and()											// AND
		.formLogin()     								// Customize the login form
		.loginPage("/myLoginPage")						// Use this login page
		.loginProcessingUrl("/authenticateDetails")		// Process the login details at this URL with POST -> Automatically authenticates based on above user config
		.permitAll()									// Allow all to see login page
		.and()
		.logout().permitAll();							// Add logout support => Automatic logout functinality at /logout POST method
	}
	
	

}
