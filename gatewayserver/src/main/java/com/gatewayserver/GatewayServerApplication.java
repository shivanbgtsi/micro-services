package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route(path ->
						path.path("bank/account/**")
								.filters(f -> f.rewritePath("/bank/accounts/(?<segment>.*)","/${segment}")
								.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNTS"))
				.route(path ->
						path.path("bank/loans/**")
								.filters(f -> f.rewritePath("/bank/loans/(?<segment>.*)","/${segment}")
										.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://LOANS"))
				.route(path ->
						path.path("bank/cards/**")
								.filters(f -> f.rewritePath("/bank/cards/(?<segment>.*)","/${segment}")
										.addRequestHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://CARDS"))
				.build();
	}

}
