package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the traffic flow prediction for route segment/time.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteTrafficFlow {
    private LocalDateTime timestamp;
    private String congestionLevel; // e.g., low, medium, high
    private Double averageSpeed; // km/h, nullable
    private Integer vehicleCount; // estimate count if available, nullable
}