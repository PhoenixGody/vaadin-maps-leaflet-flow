package software.xdev.vaadin.maps.leaflet.flow.data;

import software.xdev.vaadin.maps.leaflet.flow.data.options.HasInteractiveOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasPathOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;

public class LPolylineOptions extends LeafletOptionsBase implements HasPathOptions, HasInteractiveOptions {
    public LeafletOptionsContainerKey<Boolean> NO_CLIP = new LeafletOptionsContainerKey<>(Boolean.class, "noClip");
    public LeafletOptionsContainerKey<Double> SMOOTH_FACTOR = new LeafletOptionsContainerKey<>(Double.class, "smoothFactor");

    public void setNoClip(boolean value) {
        getLeafletOptionsContainer().putTyped(NO_CLIP, value);
    }

    public Boolean getNoClip() {
        return getLeafletOptionsContainer().getTyped(NO_CLIP);
    }

    public void setSmoothFactor(double value) {
        getLeafletOptionsContainer().putTyped(SMOOTH_FACTOR, value);
    }

    public Double getSmoothFactor() {
        return getLeafletOptionsContainer().getTyped(SMOOTH_FACTOR);
    }
}
