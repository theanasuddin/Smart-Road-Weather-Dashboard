package org.project.smartroadweatherdashboard.model.fmi;

import jakarta.xml.bind.annotation.XmlElement;

public class FmiQuantity {
    @XmlElement(name = "swe:uom")
    private UnitOfMeasure uom;
    @XmlElement(name = "swe:value")
    private String value;
}
