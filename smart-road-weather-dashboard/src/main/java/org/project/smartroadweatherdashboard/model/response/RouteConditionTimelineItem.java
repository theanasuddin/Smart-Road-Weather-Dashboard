package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteConditionTimelineItem {
    private LocalDateTime timestamp;
    private String condition;
    private Double temp;
    private Double precipitation;
    private String hazard;
}
