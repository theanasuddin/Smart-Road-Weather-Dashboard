package org.project.smartroadweatherdashboard.model.fmi;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class FmiField {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "swe:Quantity")
    private FmiQuantity quantity;
    @XmlElement(name = "swe:Category")
    private FmiCategory category;
}
