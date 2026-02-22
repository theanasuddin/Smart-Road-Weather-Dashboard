package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the road condition at a specific point or segment of a route.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteCondition {
    private String condition; // e.g., dry, icy, slippery, wet
    private Double roadSurfaceTemperature; // in Celsius, nullable
    private Double frictionEstimate; // coefficient of friction estimate, nullable
}
