package software.xdev.vaadin.maps.leaflet.flow.data.base;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class LManagedComponent implements LComponent {
    private String mapItemId;

    @Override
    public String getMapItemId() {
        return mapItemId;
    }

    public LManagedComponent() {
        mapItemId = UUID.randomUUID().toString();
    }

    public LManagedComponent(@NotNull String mapItemId) {
        this.mapItemId = mapItemId;
    }
}
