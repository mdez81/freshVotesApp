package com.freshvotes.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserDetailsServiceTest {

	@Test
	public void generate_encripted_password() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword="Password123";
		String encodedPassword= encoder.encode(rawPassword);
		//assertThat(rawPassword, not(encodedPassword));	
		assertTrue(encoder.matches(rawPassword, encodedPassword));
		System.out.println(encodedPassword);
	}

}
