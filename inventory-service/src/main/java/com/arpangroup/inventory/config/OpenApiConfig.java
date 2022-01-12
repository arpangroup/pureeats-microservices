package com.arpangroup.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIConfig(){
        ArrayList<Server> servers = new ArrayList<Server>();
        servers.add(new Server().url("http://localhost:8080").description("Local environment"));
        servers.add(new Server().url("http://example.uat.core:8080").description("UAT environment"));
        return new OpenAPI().info(
                new Info().title("User service").description("API server for accessing user information")
                .license(new License().url("http://example.com").name("Domain info"))
                .contact(new Contact().name("domains").email("domain@gmail.com").url("http://example.com"))
                .version("1.0.0")
        ).servers(servers);
    }

    private Server getServerDetails(String description, String url){
        Server qa = new Server();
        qa.setDescription(description);
        qa.setUrl(url);
        return qa;
    }
}
