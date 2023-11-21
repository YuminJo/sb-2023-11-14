package com.ll.sb20231114.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.formLogin(
				formLogin -> formLogin
					.loginPage("/member/login")
					.defaultSuccessUrl("/article/list")
			)
			.logout(
				logout -> logout
					.logoutUrl("/member/logout")
					.logoutSuccessUrl("/article/list")
			)
			.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}