package com.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

		httpSecurity
				.authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll()
						.pathMatchers("/demo/loans**").hasRole("LOANS").pathMatchers("/demo/cards/**").hasRole("CARDS")
						.pathMatchers("/demo/accounts/**").hasRole("ACCOUNTS"))
				.oauth2ResourceServer(oAuthServer -> oAuthServer
						.jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));

		/*
		 * httpSecurity .authorizeExchange(exchange ->
		 * exchange.pathMatchers(HttpMethod.GET).permitAll()
		 * .pathMatchers("/demo/loans**").authenticated().pathMatchers("/demo/cards/**")
		 * .authenticated() .pathMatchers("/demo/accounts/**").authenticated())
		 * .oauth2ResourceServer(oAuthServer ->
		 * oAuthServer.jwt(Customizer.withDefaults()));
		 */
		httpSecurity.csrf(csrf -> csrf.disable());
		return httpSecurity.build();
	}

	private ReactiveJwtAuthenticationConverterAdapter grantedAuthoritiesExtractor() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRolesConverter());
		return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
	}

}
