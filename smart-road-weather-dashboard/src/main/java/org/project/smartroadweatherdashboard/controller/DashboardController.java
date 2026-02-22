package org.project.smartroadweatherdashboard.controller;

import lombok.RequiredArgsConstructor;
import org.project.smartroadweatherdashboard.model.response.*;
import org.project.smartroadweatherdashboard.service.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/map")
    public ResponseEntity<List<DashboardMapItem>> getMapData() {
        return ResponseEntity.ok(dashboardService.getMapData());
    }

    @GetMapping("/weather")
    public ResponseEntity<List<DashboardWeatherSummary>> getWeatherData() {
        return ResponseEntity.ok(dashboardService.getWeatherSummaries());
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryCard> getSummary() {
        return ResponseEntity.ok(dashboardService.getSummary());
    }

    @GetMapping("/timeline")
    public ResponseEntity<List<DashboardTimelineItem>> getTimeline(
            @RequestParam String region,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(dashboardService.getTimeline(region, from, to));
    }
}

