package software.xdev.vaadin.maps.leaflet.flow.event;

import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import elemental.json.JsonObject;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.LMap;

import com.vaadin.flow.component.ComponentEvent;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;
import software.xdev.vaadin.maps.leaflet.flow.data.LeafBoundaries;
import software.xdev.vaadin.maps.leaflet.flow.data.convert.JsonLeafletConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

@DomEvent("map-leaflet-locationfound")
public class LocationEvent extends ComponentEvent<LMap> {

    private final LLatLng position;
    private final Double accuracy;
    private final Double altitude;
    private final Double altitudeAccuracy;
    private final Double heading;
    private final Double speed;
    private final BigInteger timestamp;

    private final LeafBoundaries boundaries;

    public LocationEvent(LMap source,
                         boolean fromClient,
                         @EventData("event.detail.latlng") JsonObject latlng,
                         @EventData("event.detail.accuracy") Double accuracy,
                         @EventData("event.detail.altitude") Double altitude,
                         @EventData("event.detail.altitudeAccuracy") Double altitudeAccuracy,
                         @EventData("event.detail.heading") Double heading,
                         @EventData("event.detail.speed") Double speed,
                         @EventData("event.detail.timestamp") String timestamp,
                         @EventData("event.detail.northEast") JsonObject northEast,
                         @EventData("event.detail.northWest") JsonObject northWest,
                         @EventData("event.detail.southEast") JsonObject southEast,
                         @EventData("event.detail.southWest") JsonObject southWest) {
        super(source, fromClient);
        this.position = JsonLeafletConverter.jsonValue2LLatLng(latlng);
        LLatLng northEastCoordinates = JsonLeafletConverter.jsonValue2LLatLng(northEast);
        LLatLng northWestCoordinates = JsonLeafletConverter.jsonValue2LLatLng(northWest);
        LLatLng southEastCoordinates = JsonLeafletConverter.jsonValue2LLatLng(southEast);
        LLatLng southWestCoordinates = JsonLeafletConverter.jsonValue2LLatLng(southWest);
        this.boundaries = new LeafBoundaries(northEastCoordinates, northWestCoordinates, southEastCoordinates, southWestCoordinates);

        this.accuracy = accuracy;
        this.altitude = altitude;
        this.altitudeAccuracy = altitudeAccuracy;
        this.heading = heading;
        this.speed = speed;
        if (timestamp == null)
            this.timestamp = null;
        else
            this.timestamp = (new BigDecimal(timestamp)).toBigInteger();//convert between big JS and Java numbers
    }

    @Nullable
    public LLatLng getPosition() {
        return position;
    }

    @Nullable
    public Double getAccuracy() {
        return accuracy;
    }

    @Nullable
    public Double getAltitude() {
        return altitude;
    }

    @Nullable
    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    @Nullable
    public Double getHeading() {
        return heading;
    }

    @Nullable
    public Double getSpeed() {
        return speed;
    }

    @Nullable
    public BigInteger getTimestamp() {
        return timestamp;
    }
}
