package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationSearchResult {
    private String city;
    private String roadName;
    private Double latitude;
    private Double longitude;
    private String displayName;
}
