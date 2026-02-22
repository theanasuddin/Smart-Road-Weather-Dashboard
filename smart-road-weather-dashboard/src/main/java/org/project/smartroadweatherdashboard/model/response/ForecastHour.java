package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastHour {
    private String time; // ISO8601 timestamp
    private Double tempC; // Temperature in Celsius
    private Double precipMm; // Precipitation in millimeters
}



