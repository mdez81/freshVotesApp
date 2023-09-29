package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			//.csrf().disable()
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/index").permitAll()
				.anyRequest().hasRole("USER")
				//.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());
		return http.build();
	}

	@Bean
	 UserDetailsService users() {
		UserDetails user = User.builder()
			.username("mdez@test.com")
			.password("asdfasdf")
			.roles("USER")
			.build();
//		UserDetails admin = User.builder()
//			.username("admin")
//			.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//			.roles("USER", "ADMIN")
//			.build();
		return new InMemoryUserDetailsManager(user); //, admin
	}
}
