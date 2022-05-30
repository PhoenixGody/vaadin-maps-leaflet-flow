package software.xdev.vaadin.maps.leaflet.flow.data;

import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;

/**
 * In Leaflet the circle options inherit the polyline options, so let's do this here the same way.
 */
public class LCircleOptions extends  LPolylineOptions {
    LeafletOptionsContainerKey<Double> RADIUS = new LeafletOptionsContainerKey<>(Double.class, "radius");

    public void setRadius(Double value) {
        getLeafletOptionsContainer().putTyped(RADIUS, value);
    }

    public @Nullable Double getRadius() {
        return getLeafletOptionsContainer().getTyped(RADIUS);
    }
}
