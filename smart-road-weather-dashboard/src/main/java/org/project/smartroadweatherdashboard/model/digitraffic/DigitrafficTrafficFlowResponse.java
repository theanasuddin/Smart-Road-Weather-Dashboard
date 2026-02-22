package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DigitrafficTrafficFlowResponse {
    private String dataUpdatedTime; // ISO8601 timestamp for data update
    private List<TrafficStation> trafficStations;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TrafficStation {
        private String stationId;
        private Location location;
        private TrafficMeasurement latestMeasurement;

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
        public static class TrafficMeasurement {
            private String timestamp; // ISO8601
            private Double averageSpeedKmH;
            private Integer vehicleCount;
            private String congestionLevel;  // e.g., low, medium, high
        }
    }
}
