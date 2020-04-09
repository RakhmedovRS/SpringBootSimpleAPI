package com.github.rakhmedovrs.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author RakhmedovRS
 * @created 08-Apr-20
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
			.withUser("user1")
			.password("{noop}password1")
			.roles("USER")
			.and()
			.withUser("admin1")
			.password("{noop}password1")
			.roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().authorizeRequests()
			.antMatchers("/survey/**").hasRole("USER")
			.antMatchers("/users/**").hasRole("USER")
			.antMatchers("/**").hasRole("ADMIN")
			.and().csrf().disable()
			.headers().frameOptions().disable();
	}
}
