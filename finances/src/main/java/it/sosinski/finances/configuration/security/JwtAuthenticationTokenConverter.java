package it.sosinski.finances.configuration.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	@Override
	public AbstractAuthenticationToken convert(@NonNull final Jwt jwt) {
		final Collection<GrantedAuthority> authorities = new HashSet<>(extractResourceRoles(jwt));
		return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim(JwtClaimNames.SUB));
	}

	private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
		final List<String> resourceAccess = jwt.getClaim("groups");
		if (resourceAccess.isEmpty()) {
			return Collections.emptySet();
		}
		return resourceAccess.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toSet());
	}
}