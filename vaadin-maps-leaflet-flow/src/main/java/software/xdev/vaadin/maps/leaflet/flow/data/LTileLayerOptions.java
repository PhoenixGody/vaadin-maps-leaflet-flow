package software.xdev.vaadin.maps.leaflet.flow.data;

import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasLayerOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;

public class LTileLayerOptions extends LeafletOptionsBase implements HasLayerOptions {
    public LeafletOptionsContainerKey<Boolean> DETECT_RETINA = new LeafletOptionsContainerKey<>(Boolean.class, "interactive");
    public LeafletOptionsContainerKey<Integer> MIN_ZOOM = new LeafletOptionsContainerKey<>(Integer.class, "minZoom");
    public LeafletOptionsContainerKey<Integer> MAX_ZOOM = new LeafletOptionsContainerKey<>(Integer.class, "maxZoom");
    public LeafletOptionsContainerKey<Integer> ZOOM_OFFSET = new LeafletOptionsContainerKey<>(Integer.class, "zoomOffset");

    // todo: add missing (see: https://leafletjs.com/reference.html#tilelayer-option):
    // subdomains
    // errorTileUrl
    // tms
    // zoomReverse
    // crossOrigin
    // referrerPolicy

    public void setDetectRetina(Boolean value) {
        getLeafletOptionsContainer().putTyped(DETECT_RETINA, value);
    }

    public @Nullable Boolean getDetectRetina() {
        return getLeafletOptionsContainer().getTyped(DETECT_RETINA);
    }

    public void setMinZoom(Integer value) {
        getLeafletOptionsContainer().putTyped(MIN_ZOOM, value);
    }

    public @Nullable Integer getMinZoom() {
        return getLeafletOptionsContainer().getTyped(MIN_ZOOM);
    }

    public void setMaxZoom(Integer value) {
        getLeafletOptionsContainer().putTyped(MAX_ZOOM, value);
    }

    public @Nullable Integer getMaxZoom() {
        return getLeafletOptionsContainer().getTyped(MAX_ZOOM);
    }

    public void setZoomOffset(Integer value) {
        getLeafletOptionsContainer().putTyped(ZOOM_OFFSET, value);
    }

    public @Nullable Integer getZoomOffset() {
        return getLeafletOptionsContainer().getTyped(ZOOM_OFFSET);
    }
}
