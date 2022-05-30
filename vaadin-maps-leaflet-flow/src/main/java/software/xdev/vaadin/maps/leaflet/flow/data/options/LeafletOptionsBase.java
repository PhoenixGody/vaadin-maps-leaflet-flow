package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.NotNull;


/**
 * The common base for all Leaflet option classes.
 * Not to be confused with the {@link LeafletOptionsShared} - see the description there for more information.
 */
public abstract class LeafletOptionsBase implements HasLeafletOptionsContainer{
    LeafletOptionsContainer leafletOptionsContainer = new LeafletOptionsContainer();

    @Override
    public @NotNull LeafletOptionsContainer getLeafletOptionsContainer() {
        return leafletOptionsContainer;
    }
}
