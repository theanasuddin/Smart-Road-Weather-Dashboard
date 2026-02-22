package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorHistoryResponse {
    private Long id; // Station ID
    private String dataUpdatedTime; // ISO8601 timestamp
    private List<HistoryDataPoint> values;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HistoryDataPoint {
        private Integer id; // Sensor ID
        private Integer stationId;
        private String measuredTime; // ISO8601 timestamp
        private Double value; // Numeric value
    }
}
