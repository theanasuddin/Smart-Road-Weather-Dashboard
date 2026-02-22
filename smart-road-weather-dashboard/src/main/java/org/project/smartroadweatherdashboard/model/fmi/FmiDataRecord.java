package org.project.smartroadweatherdashboard.model.fmi;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FmiDataRecord {
    @XmlElement(name = "sams:field")
    private List<FmiField> fields;
}
