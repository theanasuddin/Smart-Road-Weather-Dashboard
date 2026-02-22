package org.project.smartroadweatherdashboard.infrastructure;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DigitrafficFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Accept-Encoding", "gzip, deflate");
                template.header("Accept", "application/json");
                template.header("User-Agent", "SmartRoadWeatherDashboard/1.0");
                template.header("Digitraffic-User", "SmartRoadWeatherDashboard/1.0");
            }
        };
    }
}
