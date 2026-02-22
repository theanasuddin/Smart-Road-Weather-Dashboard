package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardWeatherSummary {
    private String region;
    private String weatherIcon;  // e.g., sun, rain, fog
    private Double temperature;
    private Double windSpeed;
    private Double precipitation;
}
