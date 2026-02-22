package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the weather forecast for a location/time along the route.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteWeatherForecast {
    private LocalDateTime forecastTime;
    private Double temperature; // Celsius
    private Double precipitationAmount; // mm
    private String weatherCondition; // e.g., snow, rain, fog, clear
    private Double windSpeed; // m/s, nullable
}
