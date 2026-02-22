package org.project.smartroadweatherdashboard.service;

import org.project.smartroadweatherdashboard.model.request.RoutePoint;
import org.project.smartroadweatherdashboard.model.request.RouteRequest;
import org.project.smartroadweatherdashboard.model.response.RouteCondition;
import org.project.smartroadweatherdashboard.model.response.RouteSummary;
import org.project.smartroadweatherdashboard.model.response.RouteTrafficFlow;
import org.project.smartroadweatherdashboard.model.response.RouteWeatherForecast;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RouteSummaryService {

    private static final String[] CONDITIONS = {"dry", "wet", "slippery", "icy"};
    private static final String[] WEATHER_CONDITIONS = {"clear", "rain", "snow", "fog"};
    private static final String[] CONGESTION_LEVELS = {"low", "medium", "high", "heavy"};
    private static final String[] HAZARDS = {"black ice", "accident ahead", "roadworks", "fog", "snowfall"};

    /**
     * Returns a single RouteSummary object based on the provided RouteRequest.
     */
    public RouteSummary getRouteSummary(RouteRequest req) {
        List<RoutePoint> points = req.getPoints() != null ? req.getPoints() : List.of(new RoutePoint(60.17, 24.94));
        Random rand = new Random();

        // Generate route name based on first point and total points
        String routeName = generateRouteName(points.get(0), points.size());

        // Route Conditions (current state for entire route)
        List<RouteCondition> conditions = new ArrayList<>();
        for (int i = 0; i < Math.min(3, points.size()); i++) {
            conditions.add(new RouteCondition(
                    CONDITIONS[rand.nextInt(CONDITIONS.length)],
                    Math.round((rand.nextDouble() * 20 - 10) * 10.0) / 10.0, // -10 to 10°C
                    Math.round((rand.nextDouble() * 0.8 + 0.1) * 100.0) / 100.0 // 0.1 to 0.9 friction
            ));
        }

        // Weather Forecasts (next 6 hours)
        List<RouteWeatherForecast> forecasts = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (int h = 0; h < 6; h++) {
            forecasts.add(new RouteWeatherForecast(
                    now.plusHours(h + 1),
                    Math.round((rand.nextDouble() * 15 - 5) * 10.0) / 10.0, // -5 to 10°C
                    Math.round(rand.nextDouble() * 3 * 10.0) / 10.0, // 0-3mm precipitation
                    WEATHER_CONDITIONS[rand.nextInt(WEATHER_CONDITIONS.length)],
                    Math.round(rand.nextDouble() * 15 * 10.0) / 10.0 // 0-15 m/s wind
            ));
        }

        // Traffic Flows (last 4 hours + current)
        List<RouteTrafficFlow> trafficFlows = new ArrayList<>();
        for (int h = 3; h >= 0; h--) {
            trafficFlows.add(new RouteTrafficFlow(
                    now.minusHours(h),
                    CONGESTION_LEVELS[rand.nextInt(CONGESTION_LEVELS.length)],
                    Math.round((rand.nextDouble() * 80 + 20) * 10.0) / 10.0, // 20-100 km/h
                    rand.nextInt(200) + 30 // 30-230 vehicles
            ));
        }

        List<String> hazards = new ArrayList<>();
        int hazardCount = rand.nextInt(3) + 2;
        for (int i = 0; i < hazardCount; i++) {
            hazards.add(HAZARDS[rand.nextInt(HAZARDS.length)]);
        }

        return new RouteSummary(routeName, conditions, forecasts, trafficFlows, hazards);
    }

    private String generateRouteName(RoutePoint firstPoint, int totalPoints) {
        String[] routePrefixes = {"E12", "E75", "Vt-9", "Mt-3", "Kt-55"};
        return String.format("%s Route (%.1f°N, %.1f°E → %d points)",
                routePrefixes[new Random().nextInt(routePrefixes.length)],
                firstPoint.getLatitude(),
                firstPoint.getLongitude(),
                totalPoints);
    }
}