package io.devcommunity.developer_community.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.springdoc.core.extractor.DelegatingMethodParameter.customize;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public GroupedOpenApi jwtApi(){
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("JWT API")
                        .description("API Documentation"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .servers(List.of(
                        new Server().url("http://localhost:30000")));
    }

    private Parameter createHeader(String name){
        return new Parameter()
                .in("header")
                .schema(new io.swagger.v3.oas.models.media.StringSchema())
                .name(name)
                .description(name)
                .required(false);
    }
}
