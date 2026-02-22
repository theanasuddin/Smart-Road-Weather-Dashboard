package org.project.smartroadweatherdashboard.controller;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.model.digitraffic.WeatherStationsFeatureCollection;
import org.project.smartroadweatherdashboard.model.response.ForecastHour;
import org.project.smartroadweatherdashboard.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    /**
     * Get all weather stations from Digitraffic API
     * Fetches data from https://tie.digitraffic.fi/api/weather/v1/stations
     */
    @GetMapping("/stations")
    public ResponseEntity<WeatherStationsFeatureCollection> getWeatherStations() {
        try {
            WeatherStationsFeatureCollection response = analyticsService.getWeatherStations();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get weather forecast for a specific weather station by station ID.
     * Fetches coordinates from Digitraffic API and then gets forecast from
     * Open-Meteo API.
     * 
     * @param stationId The ID of the weather station
     * @return List of ForecastHour objects (up to 7 hours starting from current
     *         hour)
     */
    @GetMapping("/stations/{stationId}/forecast")
    public ResponseEntity<List<ForecastHour>> getForecastForStation(
            @PathVariable String stationId) {
        try {
            List<ForecastHour> forecast = analyticsService.getForecastForStation(stationId);
            if (forecast.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(forecast);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get weather forecast for given coordinates.
     * Fetches forecast from Open-Meteo API using latitude and longitude.
     * 
     * @param lat Latitude of the location
     * @param lon Longitude of the location
     * @return List of ForecastHour objects (up to 7 hours starting from current
     *         hour)
     */
    @GetMapping("/forecast")
    public ResponseEntity<List<ForecastHour>> getForecastForCoordinates(
            @RequestParam Double lat,
            @RequestParam Double lon) {
        try {
            if (lat == null || lon == null) {
                return ResponseEntity.badRequest().build();
            }
            List<ForecastHour> forecast = analyticsService.getForecastForCoordinates(lat, lon);
            return ResponseEntity.ok(forecast);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
