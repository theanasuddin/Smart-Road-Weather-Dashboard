package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardSummaryCard {
    private Double temperature;
    private Double roadSurfaceFriction;
    private String trafficCongestionLevel;
    private List<String> alerts;
}
