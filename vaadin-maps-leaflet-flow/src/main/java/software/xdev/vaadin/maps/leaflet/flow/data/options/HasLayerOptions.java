package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.Nullable;

public interface HasLayerOptions extends LeafletOptionsShared {
    LeafletOptionsContainerKey<String> ATTRIBUTION = new LeafletOptionsContainerKey<>(String.class, "attribution");

    default void setAttribution(String value) {
        getLeafletOptionsContainer().putTyped(ATTRIBUTION, value);
    }

    default @Nullable String getAttribution() {
        return getLeafletOptionsContainer().getTyped(ATTRIBUTION);
    }
}