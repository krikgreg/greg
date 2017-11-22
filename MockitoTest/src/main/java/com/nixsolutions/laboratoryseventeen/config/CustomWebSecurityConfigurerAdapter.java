package com.nixsolutions.laboratoryseventeen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
 
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/", "/login", "/registrationForm").permitAll()
		.antMatchers("/admin/**").hasAuthority("Admin")
		.antMatchers("/user/**").hasAuthority("User")
		.anyRequest().permitAll();
		http.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/j_spring_security_check")
		.usernameParameter("j_username")
		.passwordParameter("j_password")
		.defaultSuccessUrl("/user-home")
		.failureUrl("/login-failed");
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and()
		.exceptionHandling().accessDeniedPage("/login");
		http.csrf().disable();
	}
}