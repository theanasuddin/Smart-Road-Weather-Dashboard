package org.project.smartroadweatherdashboard.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineRequest {
    private List<RoutePoint> points;
    private LocalDateTime from;
    private LocalDateTime to;
}
