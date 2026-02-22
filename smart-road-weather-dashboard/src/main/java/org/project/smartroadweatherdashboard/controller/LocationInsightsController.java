package org.project.smartroadweatherdashboard.controller;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.model.request.RouteRequest;
import org.project.smartroadweatherdashboard.model.request.TimelineRequest;
import org.project.smartroadweatherdashboard.model.response.LocationSearchResult;
import org.project.smartroadweatherdashboard.model.response.RouteConditionTimelineItem;
import org.project.smartroadweatherdashboard.model.response.RouteSummary;
import org.project.smartroadweatherdashboard.service.LocationInsightsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insights")
@RequiredArgsConstructor
public class LocationInsightsController {
    private final LocationInsightsService service;

    @GetMapping("/search")
    public ResponseEntity<LocationSearchResult> search(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String roadName,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {
        LocationSearchResult result = service.searchLocation(city, roadName, lat, lon);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/route-summary")
    public ResponseEntity<RouteSummary> routeSummary(@RequestBody RouteRequest req) {
        RouteSummary summary = service.getRouteSummary(req);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/route-timeline")
    public ResponseEntity<List<RouteConditionTimelineItem>> routeTimeline(@RequestBody TimelineRequest req) {
        List<RouteConditionTimelineItem> timeline = service.getTimeline(req);
        return ResponseEntity.ok(timeline);
    }
}
