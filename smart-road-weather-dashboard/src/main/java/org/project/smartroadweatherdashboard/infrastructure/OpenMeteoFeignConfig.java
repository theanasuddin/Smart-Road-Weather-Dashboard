package org.project.smartroadweatherdashboard.infrastructure;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenMeteoFeignConfig {

    @Bean
    public RequestInterceptor openMeteoRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Accept", "application/json");
                template.header("User-Agent", "SmartRoadWeatherDashboard/1.0");
            }
        };
    }
}
