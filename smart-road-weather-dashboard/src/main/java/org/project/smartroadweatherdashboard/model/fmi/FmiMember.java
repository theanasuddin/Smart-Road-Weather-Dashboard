package org.project.smartroadweatherdashboard.model.fmi;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FmiMember {
    @XmlElement(name = "sams:DataRecord")
    private FmiDataRecord dataRecord;
}
