package org.project.smartroadweatherdashboard.service;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.infrastructure.DigitrafficFeignClient;
import org.project.smartroadweatherdashboard.infrastructure.FmiFeignClient;
import org.project.smartroadweatherdashboard.model.request.RoutePoint;
import org.project.smartroadweatherdashboard.model.request.RouteRequest;
import org.project.smartroadweatherdashboard.model.request.TimelineRequest;
import org.project.smartroadweatherdashboard.model.response.*;
import org.project.smartroadweatherdashboard.model.digitraffic.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationInsightsService {

    private final DigitrafficFeignClient digitraffic;
    private final RouteSummaryService routeSummaryService;
    private final LocationSearchService locationSearchService;
    private final FmiFeignClient fmi;

    private static final String[] CONDITIONS = {"Clear", "Rainy", "Snowy", "Foggy", "Icy"};
    private static final String[] HAZARDS = {"Black Ice", "Roadworks", "Low Visibility", "Accident", "None"};

    public LocationSearchResult searchLocation(String city, String roadName, Double lat, Double lon) {
        return locationSearchService.getLocationData(city, roadName, lat, lon);
    }

    public RouteSummary getRouteSummary(RouteRequest req) {
        try {
            RouteSummary summary = new RouteSummary();
            summary.setRouteName("Route Summary");

            // Fetch weather stations nearby route points (simplified for now)
            DigitrafficWeatherStationsResponse weatherStationsResponse = digitraffic.getAllWeatherStations();

            List<RouteCondition> conditions = weatherStationsResponse.getWeatherStations().stream()
                    .map(this::mapWeatherStationToRouteCondition)
                    .collect(Collectors.toList());
            summary.setConditions(conditions);

            // Example: Fetch road weather forecasts for route area (bounding box, simplified)
            // Here use dummy bounding box now as example
            DigitrafficRoadForecastResponse roadForecastResponse = digitraffic.getRoadSectionForecast(24.0, 60.0, 25.0, 61.0);

            List<RouteWeatherForecast> forecasts = roadForecastResponse.getForecastSections().stream()
                    .flatMap(section -> section.getForecasts().stream())
                    .map(this::mapRoadWeatherForecastToRouteWeatherForecast)
                    .collect(Collectors.toList());
            summary.setWeatherForecasts(forecasts);

            // Fetch traffic flows from Digitraffic
            DigitrafficTrafficFlowResponse trafficFlowResponse = digitraffic.getAllTrafficStations();

            List<RouteTrafficFlow> trafficFlows = trafficFlowResponse.getTrafficStations().stream()
                    .map(this::mapTrafficStationToRouteTrafficFlow)
                    .collect(Collectors.toList());
            summary.setTrafficFlows(trafficFlows);

            // For hazards, in reality parse FMI warning XML or Digitraffic traffic messages
            summary.setHazards(List.of("No current hazards"));

            return summary;
        } catch (Exception e) {
            return routeSummaryService.getRouteSummary(req);
        }
    }

    public List<RouteConditionTimelineItem> getTimeline(TimelineRequest req) {
        return getRouteConditionTimelineItems(req);
    }

    // Mapper methods from Digitraffic DTOs to the model responses

    private RouteCondition mapWeatherStationToRouteCondition(DigitrafficWeatherStationsResponse.WeatherStationData station) {
        // Simplified example - real implementation should parse sensor data for conditions like temperature and friction
        return new RouteCondition("dry", 5.0, 0.8);
    }

    private RouteWeatherForecast mapRoadWeatherForecastToRouteWeatherForecast(DigitrafficRoadForecastResponse.RoadWeatherSection.WeatherForecast forecast) {
        LocalDateTime time = LocalDateTime.parse(forecast.getTime(), DateTimeFormatter.ISO_DATE_TIME);
        return new RouteWeatherForecast(time, forecast.getTemperature(), forecast.getPrecipitation(),
                forecast.getCondition(), null);
    }

    private RouteTrafficFlow mapTrafficStationToRouteTrafficFlow(DigitrafficTrafficFlowResponse.TrafficStation station) {
        DigitrafficTrafficFlowResponse.TrafficStation.TrafficMeasurement measurement = station.getLatestMeasurement();
        LocalDateTime timestamp = LocalDateTime.parse(measurement.getTimestamp(), DateTimeFormatter.ISO_DATE_TIME);
        return new RouteTrafficFlow(timestamp, measurement.getCongestionLevel(), measurement.getAverageSpeedKmH(),
                measurement.getVehicleCount());
    }

    public List<RouteConditionTimelineItem> getRouteConditionTimelineItems(TimelineRequest req) {
        List<RouteConditionTimelineItem> timelineItems = new ArrayList<>();
        Random rand = new Random();

        LocalDateTime from = req.getFrom();
        LocalDateTime to = req.getTo();

        // If from or to is null, set defaults
        if (from == null) {
            from = LocalDateTime.now().minusHours(6);
        }
        if (to == null) {
            to = LocalDateTime.now();
        }

        // Calculate time interval: create an item every hour
        long hours = Duration.between(from, to).toHours();
        if (hours <= 0) {
            hours = 1; // minimum 1 hour duration
        }

        List<RoutePoint> points = req.getPoints();
        int pointsCount = points != null ? points.size() : 1;

        for (int h = 0; h <= hours; h++) {
            // Time for this slice
            LocalDateTime timestamp = from.plusHours(h);

            for (int i = 0; i < pointsCount; i++) {
                RoutePoint point = points.get(i);

                String condition = CONDITIONS[rand.nextInt(CONDITIONS.length)];

                String hazard = HAZARDS[rand.nextInt(HAZARDS.length)];

                double baseTemp = switch (condition) {
                    case "Snowy", "Icy" -> rand.nextDouble() * 4 - 10;  // -10 to -6 C
                    case "Foggy" -> rand.nextDouble() * 6;               // 0 to 6 C
                    case "Rainy" -> rand.nextDouble() * 10 + 2;          // 2 to 12 C
                    default -> rand.nextDouble() * 8 + 8;                // Clear: 8 to 16 C
                };

                double precipitation = condition.equals("Rainy") || condition.equals("Snowy") ?
                        Math.round(rand.nextDouble() * 5 * 10.0) / 10.0 : 0.0;

                RouteConditionTimelineItem item = new RouteConditionTimelineItem(
                        timestamp,
                        condition,
                        baseTemp,
                        precipitation,
                        hazard
                );

                timelineItems.add(item);
            }
        }
        return timelineItems;
    }


}
