package com.project.memorise.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
	
	@Bean
	public SecurityFilterChain securityFilterchain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request.anyRequest().authenticated())
//		.userDetailsService(user -> );
		.formLogin(form -> form.permitAll())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
		
//		return httpSecurity.build();
	}

}
