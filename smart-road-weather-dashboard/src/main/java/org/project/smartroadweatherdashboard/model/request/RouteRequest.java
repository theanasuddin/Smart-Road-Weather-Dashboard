package org.project.smartroadweatherdashboard.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {
    private List<RoutePoint> points;
}
