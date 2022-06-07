package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.Nullable;

public interface HasMarkerOptions extends LeafletOptionsShared {
    LeafletOptionsContainerKey<Boolean> KEYBOARD = new LeafletOptionsContainerKey<>(Boolean.class, "keyboard");
    LeafletOptionsContainerKey<Integer> Z_INDEX_OFFSET = new LeafletOptionsContainerKey<>(Integer.class, "zIndexOffset");
    LeafletOptionsContainerKey<Boolean> RISE_ON_HOVER = new LeafletOptionsContainerKey<>(Boolean.class, "riseOnHover");
    LeafletOptionsContainerKey<Integer> RISE_OFFSET = new LeafletOptionsContainerKey<>(Integer.class, "riseOffset");
    LeafletOptionsContainerKey<Boolean> AUTO_PAN_ON_FOCUS = new LeafletOptionsContainerKey<>(Boolean.class, "autoPanOnFocus");

    default void setKeyboard(Boolean value) {
        getLeafletOptionsContainer().putTyped(KEYBOARD, value);
    }

    @Nullable
    default Boolean getKeyboard() {
        return getLeafletOptionsContainer().getTyped(KEYBOARD);
    }

    default void setZIndexOffset(Integer value) {
        getLeafletOptionsContainer().putTyped(Z_INDEX_OFFSET, value);
    }

    @Nullable
    default Integer getZIndexOffset() {
        return getLeafletOptionsContainer().getTyped(Z_INDEX_OFFSET);
    }

    default void setRiseOnHover(Boolean value) {
        getLeafletOptionsContainer().putTyped(RISE_ON_HOVER, value);
    }

    @Nullable
    default Boolean getRiseOnHover() {
        return getLeafletOptionsContainer().getTyped(RISE_ON_HOVER);
    }

    default void setRiseOffset(Integer value) {
        getLeafletOptionsContainer().putTyped(RISE_OFFSET, value);
    }

    @Nullable
    default Integer getRiseOffset() {
        return getLeafletOptionsContainer().getTyped(RISE_OFFSET);
    }

    default void setAutoPanOnFocus(Boolean value) {
        getLeafletOptionsContainer().putTyped(AUTO_PAN_ON_FOCUS, value);
    }

    @Nullable
    default Boolean getAutoPanOnFocus() {
        return getLeafletOptionsContainer().getTyped(AUTO_PAN_ON_FOCUS);
    }
}
