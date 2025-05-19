package com.project.memorise.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.memorise.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Config {
	
	@Bean
	public SecurityFilterChain securityFilterchain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request.anyRequest().authenticated())
//		.userDetailsService(user -> );
//		.formLogin(form -> form.permitAll())
		.httpBasic(Customizer.withDefaults()) //to enable REST access via postman or any other UI tools to request RESTAPI
//		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //to avoid csrf as new JSESSIONID will be created newly every time a request is made and it won't allow to login with form login as it will create new session and it will be in loop by request and response for the new sessions. To make it work from the form need to comment formLogin() configuration;
		.build();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
}
