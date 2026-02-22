package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteSummary {
    private String routeName;
    private List<RouteCondition> conditions; // e.g., dry, slippery, icy
    private List<RouteWeatherForecast> weatherForecasts; // hourly/daily
    private List<RouteTrafficFlow> trafficFlows; // predicted traffic speeds/fluidity
    private List<String> hazards; // accident, fog, snow etc.
}
