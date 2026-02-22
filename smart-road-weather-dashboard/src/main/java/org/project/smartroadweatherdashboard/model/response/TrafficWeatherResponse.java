package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficWeatherResponse {
    private String region;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private List<DataPoint> dataPoints;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataPoint {
        private String weatherCondition; // clear, rain, snow, fog, etc.
        private Double trafficVolume; // Traffic volume (vehicles per hour or normalized value)
    }
}