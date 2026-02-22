package org.project.smartroadweatherdashboard.infrastructure;

import org.project.smartroadweatherdashboard.model.digitraffic.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Digitraffic client
@FeignClient(name = "digitraffic", url = "https://tie.digitraffic.fi/api", configuration = DigitrafficFeignConfig.class)
public interface DigitrafficFeignClient {
    @GetMapping("/v1/weather-stations")
    List<WeatherStation> getStations();

    @GetMapping("/v1/road-weather/{roadSegmentId}")
    RoadCondition getRoadWeather(@PathVariable("roadSegmentId") String roadSegmentId);

    @GetMapping("/v1/traffic-message")
    List<TrafficMessage> getTrafficMessages();

    @GetMapping("/v1/congestion")
    CongestionLevel getCongestion();

    @GetMapping("/weather/v1/stations/data")
    DigitrafficWeatherStationsResponse getAllWeatherStations();

    @GetMapping("/weather/v1/forecast-sections-simple/forecasts")
    DigitrafficRoadForecastResponse getRoadSectionForecast(@RequestParam Double xMin, @RequestParam Double yMin,
            @RequestParam Double xMax, @RequestParam Double yMax);

    @GetMapping("/tms/v1/stations/data")
    DigitrafficTrafficFlowResponse getAllTrafficStations();

    @GetMapping("/weather/v1/stations/{stationId}/data")
    StationDataResponse getStationDataWithSensors(@PathVariable("stationId") String stationId);

    @GetMapping("/weather/v1/stations/{stationId}/data/history")
    SensorHistoryResponse getSensorHistory(
            @PathVariable("stationId") String stationId,
            @RequestParam("sensorId") String sensorId,
            @RequestParam("from") String from,
            @RequestParam("to") String to);

    @GetMapping("/weather/v1/stations")
    WeatherStationsFeatureCollection getWeatherStations();
}
