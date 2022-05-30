package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.Nullable;

public interface HasInteractiveOptions extends LeafletOptionsShared {
    LeafletOptionsContainerKey<Boolean> INTERACTIVE = new LeafletOptionsContainerKey<>(Boolean.class, "interactive");

    default void setInteractive(Boolean value) {
        getLeafletOptionsContainer().putTyped(INTERACTIVE, value);
    }

    default @Nullable Boolean getInteractive() {
        return getLeafletOptionsContainer().getTyped(INTERACTIVE);
    }
}
