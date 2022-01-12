package com.arpangroup.gatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec.path("/employee/**")
//                        .uri("http://localhost:8081/")
//                        .id()
//                ).build();
//    }
}
