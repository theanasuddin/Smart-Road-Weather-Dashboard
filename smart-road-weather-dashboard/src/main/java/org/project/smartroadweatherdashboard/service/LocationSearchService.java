package org.project.smartroadweatherdashboard.service;

import org.project.smartroadweatherdashboard.model.response.LocationSearchResult;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LocationSearchService {

    private static final String[] CITIES = {
            "Helsinki", "Tampere", "Oulu", "Rovaniemi", "Turku", "Jyväskylä"
    };

    private static final String[] ROAD_NAMES = {
            "E12", "Vt-9", "E75", "Mt-4", "Kt-55"
    };

    /**
     * Returns a LocationSearchResult using provided parameters
     */
    public LocationSearchResult getLocationData(String city, String roadName, Double lat, Double lon) {

        Random rand = new Random();

        String resolvedCity = city != null ? city : CITIES[rand.nextInt(CITIES.length)];
        String resolvedRoadName = roadName != null ? roadName : ROAD_NAMES[rand.nextInt(ROAD_NAMES.length)];
        Double resolvedLat = lat != null ? lat : 60.0 + (rand.nextDouble() * 7);  // Between 60-67 N latitude
        Double resolvedLon = lon != null ? lon : 20.0 + (rand.nextDouble() * 10); // Between 20-30 E longitude

        String displayName = String.format("%s, near %s road (Lat: %.4f, Lon: %.4f)",
                resolvedCity, resolvedRoadName, resolvedLat, resolvedLon);

        return new LocationSearchResult(
                resolvedCity,
                resolvedRoadName,
                resolvedLat,
                resolvedLon,
                displayName
        );
    }
}
