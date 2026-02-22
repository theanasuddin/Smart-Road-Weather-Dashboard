package org.project.smartroadweatherdashboard.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartRoadWeatherDashboardOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Smart Road Weather Dashboard API")
                        .description("APIs for Location-Based Insights and Smart Road Weather features")
                        .version("v1.0")
                        .contact(new Contact().name("Aurora").email("\n" +
                                "anas.uddin@tuni.fi"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Wiki Documentation")
                        .url("https://random.docs.url"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("smart-road-weather")
                .packagesToScan("org.project.smartroadweatherdashboard.controller")
                .build();
    }
}
