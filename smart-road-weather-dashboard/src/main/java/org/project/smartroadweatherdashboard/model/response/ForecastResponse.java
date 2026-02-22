package org.project.smartroadweatherdashboard.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponse {
    private List<ForecastHour> forecastHours;
}



