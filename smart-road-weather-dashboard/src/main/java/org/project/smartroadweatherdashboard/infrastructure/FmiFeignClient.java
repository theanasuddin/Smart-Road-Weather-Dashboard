package org.project.smartroadweatherdashboard.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// FMI WFS client
@FeignClient(name = "fmi", url = "https://opendata.fmi.fi/wfs")
public interface FmiFeignClient {
    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::observations::weather::multipointcoverage",
            "place={place}",
            "parameters=temperature,windspeedms,precipitationamount,weather"
    })
    String getLatestWeather(@RequestParam("place") String place);

    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::forecast::hirlam::surface::point::multipointcoverage",
            "latlon={latlon}"
    })
    String getForecast(@RequestParam("latlon") String latlon);

    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::warn::warnings::multipolygon",
            "place={place}"
    })
    String getWarnings(@RequestParam("place") String place);

    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::forecast::hirlam::surface::road::multipointcoverage",
            "roadname={roadname}"
    })
    String getRoadWeatherForecast(@RequestParam("roadname") String roadname);

    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::forecast::hirlam::surface::point::multipointcoverage",
            "latlon={latlon}"
    })
    String getPointWeatherForecast(@RequestParam("latlon") String latlon);

    @GetMapping(params = {
            "service=WFS",
            "version=2.0.0",
            "request=getFeature",
            "storedquery_id=fmi::warn::warnings::multipolygon",
            "place={place}"
    })
    String getWeatherHazards(@RequestParam("place") String place);
}
