package org.project.smartroadweatherdashboard.service;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.infrastructure.DigitrafficFeignClient;
import org.project.smartroadweatherdashboard.infrastructure.FmiFeignClient;
import org.project.smartroadweatherdashboard.model.digitraffic.*;
import org.project.smartroadweatherdashboard.model.response.*;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DigitrafficFeignClient digitrafficClient;
    private final FmiFeignClient fmiClient;

    // Map endpoint logic
    public List<DashboardMapItem> getMapData() {
        List<WeatherStation> stations = getStations();
        // For demo, assign a default "dry" condition; can extend by fetching road conditions
        return stations.stream()
                .map(s -> new DashboardMapItem(s.getStationId(), s.getStationName(), s.getLatitude(), s.getLongitude(), "dry"))
                .collect(Collectors.toList());
    }

    // Weather summary parsing example (with dummy parser for now)
    public List<DashboardWeatherSummary> getWeatherSummaries() {
        try {
            String xml = fmiClient.getLatestWeather("Helsinki");
            JAXBContext context = JAXBContext.newInstance(org.project.smartroadweatherdashboard.model.fmi.FmiFeatureCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            org.project.smartroadweatherdashboard.model.fmi.FmiFeatureCollection collection =
                    (org.project.smartroadweatherdashboard.model.fmi.FmiFeatureCollection) unmarshaller.unmarshal(reader);

            // Map to DashboardWeatherSummary (simplified demo for now, real should extract actual values)
            return List.of(new DashboardWeatherSummary("Helsinki region", "sun", 5.0, 3.0, 0.0));
        } catch (Exception ex) {
            return getDashboardWeatherSummaries();
        }
    }

    // Summary card combining sources (simplified)
    public DashboardSummaryCard getSummary() {
        try {
            double temperature = 5.0; // fetch from FMI realistically
            double friction = 0.5;    // from Digitraffic road conditions
            String congestion = digitrafficClient.getCongestion().getLevel();
            List<TrafficMessage> messages = digitrafficClient.getTrafficMessages();

            List<String> alerts = messages.stream()
                    .filter(m -> m.getType().equalsIgnoreCase("accident") || m.getType().equalsIgnoreCase("closure"))
                    .map(TrafficMessage::getDescription)
                    .collect(Collectors.toList());

            if (alerts.isEmpty()) {
                alerts.add("No alerts");
            }

            return new DashboardSummaryCard(temperature, friction, congestion, alerts);
        } catch (Exception ex) {
            return getDashboardSummaryCard();
        }
    }

    // Timeline data aggregator (simplified)
    public List<DashboardTimelineItem> getTimeline(String region, LocalDateTime from, LocalDateTime to) {
        LocalDateTime now = LocalDateTime.now();
        return List.of(
                new DashboardTimelineItem(now.minusHours(6), -3.2, "temperature"),
                new DashboardTimelineItem(now.minusHours(5), -2.8, "temperature"),
                new DashboardTimelineItem(now.minusHours(4), -2.1, "temperature"),
                new DashboardTimelineItem(now.minusHours(3), -1.5, "temperature"),
                new DashboardTimelineItem(now.minusHours(2), -0.9, "temperature"),
                new DashboardTimelineItem(now.minusHours(1), -0.3, "temperature"),
                new DashboardTimelineItem(now, 0.1, "temperature"),

                new DashboardTimelineItem(now.minusHours(6), 0.0, "precipitation"),
                new DashboardTimelineItem(now.minusHours(5), 0.1, "precipitation"),
                new DashboardTimelineItem(now.minusHours(4), 0.8, "precipitation"),
                new DashboardTimelineItem(now.minusHours(3), 1.2, "precipitation"),
                new DashboardTimelineItem(now.minusHours(2), 0.9, "precipitation"),
                new DashboardTimelineItem(now.minusHours(1), 0.4, "precipitation"),
                new DashboardTimelineItem(now, 0.2, "precipitation"),

                new DashboardTimelineItem(now.minusHours(6), 8.5, "wind_speed"),
                new DashboardTimelineItem(now.minusHours(5), 9.2, "wind_speed"),
                new DashboardTimelineItem(now.minusHours(4), 10.1, "wind_speed"),
                new DashboardTimelineItem(now.minusHours(3), 11.3, "wind_speed"),
                new DashboardTimelineItem(now.minusHours(2), 9.8, "wind_speed"),
                new DashboardTimelineItem(now.minusHours(1), 8.9, "wind_speed"),
                new DashboardTimelineItem(now, 7.6, "wind_speed")
        );
    }

    public List<WeatherStation> getStations() {
        return List.of(
                new WeatherStation(
                        "FI-HEL-001",
                        "Helsinki Kumpula Weather Station",
                        60.2030,
                        24.9610
                ),
                new WeatherStation(
                        "FI-TRE-002",
                        "Tampere Härmälä Road Station",
                        61.4789,
                        23.7675
                ),
                new WeatherStation(
                        "FI-OU-003",
                        "Oulu Airport Road Weather Station",
                        64.9300,
                        25.3546
                ),
                new WeatherStation(
                        "FI-ROV-004",
                        "Rovaniemi Arctic Circle Station",
                        66.5039,
                        25.7294
                ),
                new WeatherStation(
                        "FI-TKU-005",
                        "Turku Ring Road Station",
                        60.4518,
                        22.2666
                )
        );
    }

    public List<DashboardWeatherSummary> getDashboardWeatherSummaries() {
        return List.of(
                new DashboardWeatherSummary("Helsinki Region", "sun", 5.6, 12.3, 0.0),
                new DashboardWeatherSummary("Tampere Region", "rain", 3.1, 8.7, 2.4),
                new DashboardWeatherSummary("Oulu Region", "fog", 1.0, 5.5, 0.1),
                new DashboardWeatherSummary("Rovaniemi Region", "snow", -3.4, 15.0, 3.2),
                new DashboardWeatherSummary("Turku Region", "cloud", 4.8, 10.1, 0.5)
        );
    }

    public DashboardSummaryCard getDashboardSummaryCard() {
        return new DashboardSummaryCard(
                -2.3,                                    // Temperature (°C) - cold winter conditions
                0.25,                                    // Road surface friction (0.0 = ice, 1.0 = dry)
                "moderate",                              // Traffic congestion level
                Arrays.asList(                           // Active alerts
                        "🛣️ Black ice detected on E12 highway",
                        "🌨️ Snow accumulation expected next 2 hours",
                        "🚗 Reduced visibility due to fog"
                )
        );
    }
}
