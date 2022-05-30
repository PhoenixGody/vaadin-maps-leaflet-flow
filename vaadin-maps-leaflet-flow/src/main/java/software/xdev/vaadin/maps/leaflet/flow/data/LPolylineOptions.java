package software.xdev.vaadin.maps.leaflet.flow.data;

import software.xdev.vaadin.maps.leaflet.flow.data.options.HasInteractiveOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasPathOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;

public class LPolylineOptions extends LeafletOptionsBase implements HasPathOptions, HasInteractiveOptions {
    LeafletOptionsContainerKey<Boolean> NO_CLIP = new LeafletOptionsContainerKey<>(Boolean.class, "noClip");
    LeafletOptionsContainerKey<Double> SMOOTH_FACTOR = new LeafletOptionsContainerKey<>(Double.class, "smoothFactor");

    void setNoClip(boolean value) {
        getLeafletOptionsContainer().putTyped(NO_CLIP, value);
    }

    Boolean getNoClip() {
        return getLeafletOptionsContainer().getTyped(NO_CLIP);
    }

    void setSmoothFactor(double value) {
        getLeafletOptionsContainer().putTyped(SMOOTH_FACTOR, value);
    }

    Double getSmoothFactor() {
        return getLeafletOptionsContainer().getTyped(SMOOTH_FACTOR);
    }
}
