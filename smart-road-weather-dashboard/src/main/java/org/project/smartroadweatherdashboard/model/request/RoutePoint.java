package org.project.smartroadweatherdashboard.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a geographic point on a route with latitude and longitude coordinates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutePoint {
    private Double latitude;
    private Double longitude;
}
