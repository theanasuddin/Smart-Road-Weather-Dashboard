package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DigitrafficRoadForecastResponse {
    private String requestTime; // ISO8601 of response time
    private List<RoadWeatherSection> forecastSections;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoadWeatherSection {
        private String sectionId;
        private List<Location> roadPoints; // List of GPS points defining the road section
        private List<WeatherForecast> forecasts; // forecast data for this road section

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Location {
            private Double latitude;
            private Double longitude;
        }

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class WeatherForecast {
            private String time; // ISO8601 forecast time
            private String condition; // dry, wet, snow, ice etc.
            private Double temperature;  // Celsius
            private Double precipitation; // mm
            private Double roadSurfaceTemp; // Celsius
            private Double friction; // coefficient
        }
    }
}
