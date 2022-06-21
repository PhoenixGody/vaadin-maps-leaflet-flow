package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.Nullable;

public interface HasClassNameOptions<TYPE> extends LeafletOptionsShared {
    LeafletOptionsContainerKey<String> CLASSNAME = new LeafletOptionsContainerKey<>(String.class, "className");

    default TYPE setClassName(String value) {
        getLeafletOptionsContainer().putTyped(CLASSNAME, value);
        return (TYPE) this;
    }

    default @Nullable String getClassName() {
        return getLeafletOptionsContainer().getTyped(CLASSNAME);
    }
}
