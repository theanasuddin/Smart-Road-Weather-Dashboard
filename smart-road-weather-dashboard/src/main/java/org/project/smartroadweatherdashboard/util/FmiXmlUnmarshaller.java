package org.project.smartroadweatherdashboard.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.project.smartroadweatherdashboard.model.fmi.FmiFeatureCollection;

import java.io.File;
import java.io.FileInputStream;

public class FmiXmlUnmarshaller {

    public static FmiFeatureCollection unmarshalXmlFile(String xmlFilePath) {
        try {
            // Create JAXB context for the package/class containing JAXB classes
            JAXBContext jaxbContext = JAXBContext.newInstance(FmiFeatureCollection.class);

            // Create the unmarshaller
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Read the XML from the file
            File xmlFile = new File(xmlFilePath);
            // Or if XML is obtained from a String, use StringReader instead:
            // StringReader reader = new StringReader(xmlContent);
            // return (FmiFeatureCollection) unmarshaller.unmarshal(reader);

            // Unmarshal XML file to FmiFeatureCollection object
            FmiFeatureCollection collection = (FmiFeatureCollection) unmarshaller.unmarshal(new FileInputStream(xmlFile));

            return collection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to unmarshal FMI XML", e);
        }
    }
}
