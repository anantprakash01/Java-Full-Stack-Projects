package com.pritam.fabrication.ecommerce.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;




@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http
			.sessionManagement(
				session -> session.sessionCreationPolicy
				(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth->auth.requestMatchers("/auth/**").permitAll())
			.authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated()
					.anyRequest().permitAll())
			.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
			.csrf(AbstractHttpConfigurer::disable)
			.cors(corsCustomizer-> corsCustomizer.configurationSource(corsConfigurationSource()))
			.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

		return http.build();
	}

	private CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration= new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList(
				"http://localhost:3000",
				"http://localhost:4200"
				));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setExposedHeaders(Arrays.asList("Authorization"));
		configuration.setMaxAge(3600L);


		 var source = new UrlBasedCorsConfigurationSource();
		    source.registerCorsConfiguration("/**", configuration);
		    return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
