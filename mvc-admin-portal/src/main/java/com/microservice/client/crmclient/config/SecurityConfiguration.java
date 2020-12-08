package com.microservice.client.crmclient.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired javax.sql.DataSource dataSource;
	 */
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         
		/*in memeory*/
		/*-auth.inMemoryAuthentication()
		.withUser("user")
		.password("user")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");*/
		
		/*-JDBC authentication*/
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource);
		 */
		
		/*-JPA*/
		auth.userDetailsService(userDetailsService);
		
		
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		//this is for plain text, but this we should not not use in production
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	/*-to configure the Authroriation */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		//permit all is mainly used for css and other static resources
		//.antMatchers("/","").permitAll()
		.antMatchers("/customer/admin").hasRole("ADMIN")
		.antMatchers("/customer/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll()
		//.antMatchers("/**").hasAnyRole("ADMIN")
		.and()
		.formLogin();
		
	}
}
