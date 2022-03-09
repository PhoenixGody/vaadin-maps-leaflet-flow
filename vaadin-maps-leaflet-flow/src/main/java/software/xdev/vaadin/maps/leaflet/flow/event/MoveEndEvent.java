package software.xdev.vaadin.maps.leaflet.flow.event;

import com.vaadin.flow.component.ComponentEvent;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.LeafBoundaries;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;

public class MoveEndEvent extends ComponentEvent<LMap> {
    private final LeafBoundaries boundaries;
    private final LLatLng center;

    public MoveEndEvent(final LMap source, final boolean fromClient,
                        double centerLat, double centerLng,
                        double northEastLat, double northEastLng,
                        double northWestLat, double northWestLng,
                        double southEastLat, double southEastLng,
                        double southWestLat, double southWestLng) {
        super(source, fromClient);
        boundaries = LeafBoundaries.of(northEastLat, northEastLng, northWestLat, northWestLng, southEastLat, southEastLng, southWestLat, southWestLng);
        center = new LLatLng(centerLat, centerLng);
    }

    public LeafBoundaries getBoundaries() {
        return boundaries;
    }

    public LLatLng getCenter() {
        return center;
    }
}
