package org.project.smartroadweatherdashboard.model.fmi;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * Root class for the WFS FeatureCollection XML response from FMI.
 */
@XmlRootElement(name = "FeatureCollection", namespace = "http://www.opengis.net/wfs/2.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class FmiFeatureCollection {

    @XmlElement(name = "member", namespace = "http://www.opengis.net/wfs/2.0")
    private List<FmiMember> members;

    public List<FmiMember> getMembers() {
        return members;
    }

    public void setMembers(List<FmiMember> members) {
        this.members = members;
    }

    /**
     * Represents a member feature within the feature collection.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class FmiMember {

        @XmlElement(name = "PointTimeSeriesObservation", namespace = "http://inspire.ec.europa.eu/schemas/omso/3.0")
        private PointTimeSeriesObservation pointTimeSeriesObservation;

        public PointTimeSeriesObservation getPointTimeSeriesObservation() {
            return pointTimeSeriesObservation;
        }

        public void setPointTimeSeriesObservation(PointTimeSeriesObservation pointTimeSeriesObservation) {
            this.pointTimeSeriesObservation = pointTimeSeriesObservation;
        }
    }

    /**
     * Represents a point time series observation element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PointTimeSeriesObservation {

        @XmlAttribute(name = "gml:id")
        private String id;

        @XmlElement(name = "phenomenonTime", namespace = "http://www.opengis.net/om/2.0")
        private PhenomenonTime phenomenonTime;

        @XmlElement(name = "resultTime", namespace = "http://www.opengis.net/om/2.0")
        private ResultTime resultTime;

        @XmlElement(name = "procedure", namespace = "http://www.opengis.net/om/2.0")
        private Procedure procedure;

        @XmlElement(name = "parameter", namespace = "http://www.opengis.net/om/2.0")
        private Parameter parameter;

        @XmlElement(name = "result", namespace = "http://www.opengis.net/om/2.0")
        private Result result;

        public String getId() {
            return id;
        }

        public PhenomenonTime getPhenomenonTime() {
            return phenomenonTime;
        }

        public ResultTime getResultTime() {
            return resultTime;
        }

        public Procedure getProcedure() {
            return procedure;
        }

        public Parameter getParameter() {
            return parameter;
        }

        public Result getResult() {
            return result;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setPhenomenonTime(PhenomenonTime phenomenonTime) {
            this.phenomenonTime = phenomenonTime;
        }

        public void setResultTime(ResultTime resultTime) {
            this.resultTime = resultTime;
        }

        public void setProcedure(Procedure procedure) {
            this.procedure = procedure;
        }

        public void setParameter(Parameter parameter) {
            this.parameter = parameter;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    /**
     * Represents phenomenom time element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PhenomenonTime {
        @XmlElement(name = "TimePeriod", namespace = "http://www.opengis.net/gml/3.2")
        private TimePeriod timePeriod;

        public TimePeriod getTimePeriod() {
            return timePeriod;
        }

        public void setTimePeriod(TimePeriod timePeriod) {
            this.timePeriod = timePeriod;
        }
    }

    /**
     * Represents result time element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResultTime {
        @XmlElement(name = "TimeInstant", namespace = "http://www.opengis.net/gml/3.2")
        private TimeInstant timeInstant;

        public TimeInstant getTimeInstant() {
            return timeInstant;
        }

        public void setTimeInstant(TimeInstant timeInstant) {
            this.timeInstant = timeInstant;
        }
    }

    /**
     * Represents procedure element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Procedure {
        @XmlAttribute(name = "xlink:href", namespace = "http://www.w3.org/1999/xlink")
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    /**
     * Represents parameter element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Parameter {
        @XmlElement(name = "NamedValue", namespace = "http://www.opengis.net/om/2.0")
        private NamedValue namedValue;

        public NamedValue getNamedValue() {
            return namedValue;
        }

        public void setNamedValue(NamedValue namedValue) {
            this.namedValue = namedValue;
        }
    }

    /**
     * Represents named value structure.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NamedValue {
        @XmlElement(name = "name", namespace = "http://www.opengis.net/om/2.0")
        private String name;

        @XmlElement(name = "value", namespace = "http://www.opengis.net/om/2.0")
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * Represents result element which contains the observed values.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Result {
        @XmlElement(name = "MeasurementTimeseries", namespace = "http://www.opengis.net/sensorml/2.0")
        private MeasurementTimeseries measurementTimeseries;

        public MeasurementTimeseries getMeasurementTimeseries() {
            return measurementTimeseries;
        }

        public void setMeasurementTimeseries(MeasurementTimeseries measurementTimeseries) {
            this.measurementTimeseries = measurementTimeseries;
        }
    }

    /**
     * Represents measurement timeseries element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MeasurementTimeseries {
        @XmlElement(name = "MeasurementTVP", namespace = "http://www.opengis.net/sensorml/2.0")
        private List<MeasurementTVP> measurementTVPs;

        public List<MeasurementTVP> getMeasurementTVPs() {
            return measurementTVPs;
        }

        public void setMeasurementTVPs(List<MeasurementTVP> measurementTVPs) {
            this.measurementTVPs = measurementTVPs;
        }
    }

    /**
     * Represents measurement time value pair element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class MeasurementTVP {
        @XmlElement(name = "time", namespace = "http://www.opengis.net/swe/2.0")
        private String time;

        @XmlElement(name = "value", namespace = "http://www.opengis.net/swe/2.0")
        private String value;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * Represents GML TimePeriod element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TimePeriod {
        @XmlElement(name = "beginPosition", namespace = "http://www.opengis.net/gml/3.2")
        private String beginPosition;

        @XmlElement(name = "endPosition", namespace = "http://www.opengis.net/gml/3.2")
        private String endPosition;

        public String getBeginPosition() {
            return beginPosition;
        }

        public void setBeginPosition(String beginPosition) {
            this.beginPosition = beginPosition;
        }

        public String getEndPosition() {
            return endPosition;
        }

        public void setEndPosition(String endPosition) {
            this.endPosition = endPosition;
        }
    }

    /**
     * Represents GML TimeInstant element.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TimeInstant {
        @XmlElement(name = "timePosition", namespace = "http://www.opengis.net/gml/3.2")
        private String timePosition;

        public String getTimePosition() {
            return timePosition;
        }

        public void setTimePosition(String timePosition) {
            this.timePosition = timePosition;
        }
    }
}
