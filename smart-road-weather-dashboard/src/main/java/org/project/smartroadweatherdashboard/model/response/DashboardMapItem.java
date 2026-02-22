package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardMapItem {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String roadCondition;
}
