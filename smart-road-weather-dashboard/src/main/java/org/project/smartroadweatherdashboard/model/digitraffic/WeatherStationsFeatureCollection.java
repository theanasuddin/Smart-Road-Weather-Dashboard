package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStationsFeatureCollection {
    private String type;
    private String dataUpdatedTime;
    private List<WeatherStationFeature> features;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherStationFeature {
        private String type;
        private Long id;
        private Geometry geometry;
        private StationProperties properties;

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Geometry {
            private String type;
            private List<Double> coordinates; // [longitude, latitude, elevation]
        }

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class StationProperties {
            private Long id;
            private String name;
            private String collectionStatus;
            private String state;
            private String dataUpdatedTime;
        }
    }
}





