package com.freshvotes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailService;
	

	@Bean
	public static PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();		
		}

	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			//.csrf(csrf -> csrf.disable()) -- enable it
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				//.requestMatchers("/login").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().hasRole("USER")
				//.anyRequest().authenticated()	
			)
			.formLogin((form) -> form
				.loginPage("/login")	
//				.loginProcessingUrl("/login")
               .defaultSuccessUrl("/dashboard")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll()); //.logoutUrl("/logout")
		return http.build();
	}

//	@Bean
//	 UserDetailsService users() {
//		UserDetails user = User.builder()
//			.username("user")
//			//.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//			.password(getPasswordEncoder().encode("password"))
//			.roles("USER")
//			.build();
////		UserDetails admin = User.builder()
////			.username("admin")
////			//.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
////			.password(getPasswordEncoder().encode("password"))
////			.roles("USER", "ADMIN")
////			.build();
//		return new InMemoryUserDetailsManager(user); //, admin
//	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
		.passwordEncoder(getPasswordEncoder());
	}
	
	
		
 }

	
	
	

