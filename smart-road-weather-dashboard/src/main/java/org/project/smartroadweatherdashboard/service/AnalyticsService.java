package org.project.smartroadweatherdashboard.service;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.infrastructure.DigitrafficFeignClient;
import org.project.smartroadweatherdashboard.infrastructure.OpenMeteoFeignClient;
import org.project.smartroadweatherdashboard.model.digitraffic.WeatherStationsFeatureCollection;
import org.project.smartroadweatherdashboard.model.response.ForecastHour;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final DigitrafficFeignClient digitrafficClient;
    private final OpenMeteoFeignClient openMeteoClient;

    /**
     * Fetches all weather stations from Digitraffic API.
     * 
     * @return WeatherStationsFeatureCollection containing all weather stations
     */
    public WeatherStationsFeatureCollection getWeatherStations() {
        return digitrafficClient.getWeatherStations();
    }

    /**
     * Fetches weather forecast for a given latitude and longitude from Open-Meteo
     * API.
     * Extracts coordinates from Digitraffic weather stations and fetches forecast
     * data.
     * Returns up to 7 forecast hours starting from the current hour.
     * 
     * @param lat Latitude of the location
     * @param lon Longitude of the location
     * @return List of ForecastHour objects containing time, temperature, and
     *         precipitation
     */
    public List<ForecastHour> getForecastForCoordinates(Double lat, Double lon) {
        try {
            // Fetch forecast from Open-Meteo API
            OpenMeteoFeignClient.OpenMeteoResponse response = openMeteoClient.getForecast(
                    lat,
                    lon,
                    "temperature_2m,precipitation",
                    "auto",
                    1 // forecast_days = 1
            );

            if (response == null || response.getHourly() == null) {
                return new ArrayList<>();
            }

            List<String> times = response.getHourly().getTime();
            List<Double> temps = response.getHourly().getTemperature_2m();
            List<Double> precs = response.getHourly().getPrecipitation();

            if (times == null || temps == null || precs == null || times.isEmpty()) {
                return new ArrayList<>();
            }

            // Find the current hour index
            Instant now = Instant.now();
            String nowHourIsoPrefix = now.toString().substring(0, 13); // YYYY-MM-DDTHH
            int startIndex = -1;
            for (int i = 0; i < times.size(); i++) {
                if (times.get(i).startsWith(nowHourIsoPrefix)) {
                    startIndex = i;
                    break;
                }
            }
            if (startIndex == -1) {
                startIndex = 0; // If current hour not found, start from beginning
            }

            // Build result with up to 7 points
            List<ForecastHour> result = new ArrayList<>();
            int maxPoints = 7;
            for (int i = 0; i < maxPoints; i++) {
                int idx = startIndex + i;
                if (idx >= times.size()) {
                    break;
                }

                result.add(new ForecastHour(
                        times.get(idx),
                        temps.get(idx),
                        precs.get(idx)));
            }

            return result;
        } catch (Exception e) {
            System.err.println("Error fetching forecast for coordinates (" + lat + ", " + lon + "): " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Fetches weather forecast for a specific weather station by its ID.
     * First fetches the station from Digitraffic to get coordinates, then fetches
     * forecast.
     * 
     * @param stationId The ID of the weather station
     * @return List of ForecastHour objects containing time, temperature, and
     *         precipitation
     */
    public List<ForecastHour> getForecastForStation(String stationId) {
        try {
            // Fetch weather stations to find the one with matching ID
            WeatherStationsFeatureCollection stations = digitrafficClient.getWeatherStations();

            if (stations == null || stations.getFeatures() == null) {
                return new ArrayList<>();
            }

            // Find the station with matching ID
            WeatherStationsFeatureCollection.WeatherStationFeature station = stations.getFeatures().stream()
                    .filter(f -> f.getProperties() != null &&
                            f.getProperties().getId() != null &&
                            String.valueOf(f.getProperties().getId()).equals(stationId))
                    .findFirst()
                    .orElse(null);

            if (station == null || station.getGeometry() == null ||
                    station.getGeometry().getCoordinates() == null ||
                    station.getGeometry().getCoordinates().size() < 2) {
                return new ArrayList<>();
            }

            // Extract coordinates: [longitude, latitude, elevation]
            List<Double> coords = station.getGeometry().getCoordinates();
            Double lon = coords.get(0);
            Double lat = coords.get(1);

            if (lat == null || lon == null) {
                return new ArrayList<>();
            }

            // Fetch forecast using coordinates
            return getForecastForCoordinates(lat, lon);
        } catch (Exception e) {
            System.err.println("Error fetching forecast for station " + stationId + ": " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}