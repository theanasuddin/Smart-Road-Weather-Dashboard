package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationDataResponse {
    private Long id; // Station ID
    private String dataUpdatedTime; // ISO8601 timestamp
    private List<SensorValue> sensorValues;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SensorValue {
        private Integer id; // Sensor ID
        private Integer stationId;
        private String name;
        private String shortName;
        private String measuredTime; // ISO8601 timestamp
        private Double value;
        private String unit;
        private String sensorValueDescriptionFi;
        private String sensorValueDescriptionEn;
    }
}

