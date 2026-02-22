package org.project.smartroadweatherdashboard.model.fmi;

import jakarta.xml.bind.annotation.XmlElement;

public class FmiCategory {
    @XmlElement(name = "swe:value")
    private String value;
}
