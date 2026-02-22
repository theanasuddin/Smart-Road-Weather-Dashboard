package org.project.smartroadweatherdashboard.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationSearchRequest {
    private String city;
    private String roadName;
    private Double latitude;
    private Double longitude;
}
