package org.project.smartroadweatherdashboard.model.digitraffic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficMessage {
    private String id;
    private String type; // accident, closure etc.
    private String description;
    private String startTime;
    private String endTime;
}
