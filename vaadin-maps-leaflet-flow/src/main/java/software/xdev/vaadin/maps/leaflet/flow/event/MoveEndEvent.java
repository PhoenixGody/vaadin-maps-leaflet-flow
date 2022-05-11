package software.xdev.vaadin.maps.leaflet.flow.event;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import elemental.json.JsonObject;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.LeafBoundaries;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;
import software.xdev.vaadin.maps.leaflet.flow.data.convert.JsonLeafletConverter;

@DomEvent("map-leaflet-viewport-move-end")
public class MoveEndEvent extends ComponentEvent<LMap> {
    private final LeafBoundaries boundaries;
    private final LLatLng center;

    public MoveEndEvent(final LMap source, final boolean fromClient,
                        @EventData("event.detail.center") JsonObject center,
                        @EventData("event.detail.northEast") JsonObject northEast,
                        @EventData("event.detail.northWest") JsonObject northWest,
                        @EventData("event.detail.southEast") JsonObject southEast,
                        @EventData("event.detail.southWest") JsonObject southWest
                        ) {
        super(source, fromClient);
        this.center = JsonLeafletConverter.jsonValue2LLatLng(center);
        LLatLng northEastCoordinates = JsonLeafletConverter.jsonValue2LLatLng(northEast);
        LLatLng northWestCoordinates = JsonLeafletConverter.jsonValue2LLatLng(northWest);
        LLatLng southEastCoordinates = JsonLeafletConverter.jsonValue2LLatLng(southEast);
        LLatLng southWestCoordinates = JsonLeafletConverter.jsonValue2LLatLng(southWest);
        this.boundaries = new LeafBoundaries(northEastCoordinates, northWestCoordinates, southEastCoordinates, southWestCoordinates);
    }

    public LeafBoundaries getBoundaries() {
        return boundaries;
    }

    public LLatLng getCenter() {
        return center;
    }
}
