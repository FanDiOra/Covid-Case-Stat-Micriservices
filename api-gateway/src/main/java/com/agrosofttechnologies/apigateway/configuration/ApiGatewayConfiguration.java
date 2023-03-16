package com.agrosofttechnologies.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
				
		return builder.routes()
				.route(p -> p
						.path("/agrosoft/covid/cases")
						.uri("lb://covid-case-registration"))
				.route(p -> p
						.path("/agrosoft/covid/cases/**")
						.uri("lb://covid-case-registration"))
				.route(p -> p
						.path("/agrosoft/covid/reports")
						.uri("lb://covid-reports"))
				.route(p -> p
						.path("/agrosoft/covid/reports/**")
						.uri("lb://covid-reports"))
				.build();
	}
}