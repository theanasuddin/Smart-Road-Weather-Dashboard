package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DigitrafficWeatherStationsResponse {
    private List<WeatherStationData> weatherStations;
    private String dataUpdatedTime; // ISO8601 timestamp of last update

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherStationData {
        private String stationId;
        private String stationName;
        private Location location;
        private List<StationSensorData> sensors;

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
        public static class StationSensorData {
            private String sensorId;
            private String sensorType;
            private Double value;
            private String unit;
            private String timeStamp; // ISO8601
        }
    }
}
