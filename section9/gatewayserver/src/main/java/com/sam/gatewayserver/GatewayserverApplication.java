package com.sam.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator samBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p-> p
						.path("/sambank/accounts/**")
				        .filters(f->f.rewritePath("/sambank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p-> p
						.path("/sambank/loans/**")
						.filters(f->f.rewritePath("/sambank/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p-> p
						.path("/sambank/cards/**")
						.filters(f->f.rewritePath("/sambank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();
	}

}
