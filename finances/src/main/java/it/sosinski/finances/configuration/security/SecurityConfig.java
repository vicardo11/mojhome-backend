package it.sosinski.finances.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationTokenConverter jwtAuthenticationTokenConverter;

	@Bean
	public SecurityFilterChain resourceServerFilterChain(final HttpSecurity http) throws Exception {
		return http

				.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
						.anyRequest().hasRole("user"))
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
						jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationTokenConverter)))
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(Customizer.withDefaults())
				.build();
	}

	@Bean
	@Profile("test")
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}

}
