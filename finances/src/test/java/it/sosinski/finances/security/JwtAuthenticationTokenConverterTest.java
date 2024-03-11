package it.sosinski.finances.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import it.sosinski.finances.configuration.security.JwtAuthenticationTokenConverter;

class JwtAuthenticationTokenConverterTest {

	private JwtAuthenticationTokenConverter tokenConverter;

	@BeforeEach
	void setUp() {
		tokenConverter = new JwtAuthenticationTokenConverter();
	}

	@Test
	void shouldReturnJwtAuthenticationTokenWithAuthoritiesAndSubject() {
		// Given
		final Jwt jwt = mock(Jwt.class);
		when(jwt.getClaim(JwtClaimNames.SUB)).thenReturn("user123");
		when(jwt.getClaim("groups")).thenReturn(List.of("ADMIN", "USER"));

		// When
		final AbstractAuthenticationToken result = tokenConverter.convert(jwt);

		// Then
		assertThat(result).isInstanceOf(JwtAuthenticationToken.class);
		final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) result;
		assertThat(jwtAuthenticationToken.getAuthorities()).containsExactlyInAnyOrder(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER")
		);
		assertThat(jwtAuthenticationToken.getToken()).isEqualTo(jwt);
		assertThat(jwtAuthenticationToken.getName()).isEqualTo("user123");
	}

	@Test
	void shouldReturnJwtAuthenticationTokenWithEmptyAuthoritiesWhenGroupsClaimIsEmpty() {
		// Given
		final Jwt jwt = mock(Jwt.class);
		when(jwt.getClaim(JwtClaimNames.SUB)).thenReturn("user123");
		when(jwt.getClaim("groups")).thenReturn(Collections.emptyList());

		// When
		final AbstractAuthenticationToken result = tokenConverter.convert(jwt);

		// Then
		assertThat(result).isInstanceOf(JwtAuthenticationToken.class);
		final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) result;
		assertThat(jwtAuthenticationToken.getAuthorities()).isEmpty();
		assertThat(jwtAuthenticationToken.getToken()).isEqualTo(jwt);
		assertThat(jwtAuthenticationToken.getName()).isEqualTo("user123");
	}
}