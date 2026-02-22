package org.project.smartroadweatherdashboard.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "openmeteo", url = "https://api.open-meteo.com/v1", configuration = OpenMeteoFeignConfig.class)
public interface OpenMeteoFeignClient {

    @GetMapping("/forecast")
    OpenMeteoResponse getForecast(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("hourly") String hourly,
            @RequestParam("timezone") String timezone,
            @RequestParam("forecast_days") Integer forecastDays);

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OpenMeteoResponse {
        private Double latitude;
        private Double longitude;
        private Double generationtime_ms;
        private Integer utc_offset_seconds;
        private String timezone;
        private String timezone_abbreviation;
        private Double elevation;
        private HourlyUnits hourly_units;
        private HourlyData hourly;

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class HourlyUnits {
            private String time;
            private String temperature_2m;
            private String precipitation;
        }

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class HourlyData {
            private List<String> time;
            private List<Double> temperature_2m;
            private List<Double> precipitation;
        }
    }
}
