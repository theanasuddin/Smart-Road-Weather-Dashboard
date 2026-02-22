package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DashboardTimelineItem {
    private LocalDateTime timestamp;
    private Double value;
    private String dataType; // e.g., temperature, precipitation
}