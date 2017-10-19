package com.nixsolutions.laboratorysixteen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

import com.nixsolutions.laboratorysixteen.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	DataSource dataSource;
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
 
	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 auth
         .userDetailsService(userDetailsService);
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select login,password, enabled from users where login=?")
//				.authoritiesByUsernameQuery("select login, name from users NATURAL JOIN ROLES where login=?");
////				("select login, name from users NATURAL JOIN ROLES where login=?")
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
        .disable()
        .authorizeRequests()
		.antMatchers("/", "/login", "/registrationForm", "/registration").permitAll()
		.antMatchers("/laboratorysixteen/admin/**").hasAuthority("Admin")
		.antMatchers("/laboratorysixteen/user/**").hasAuthority("User")
		.anyRequest().permitAll();
	}
}