package software.xdev.vaadin.maps.leaflet.flow;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.base.LComponent;

import java.util.UUID;

public abstract class LManagedComponent implements LComponent {
    private String mapItemId;
    private LMap attachedMap;

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

    @NotNull
    public LMap getAttachedMap() {
        assert attachedMap != null;
        return attachedMap;
    }

    public boolean isAttachedToMap() {
        return attachedMap != null;
    }


    protected void setAttachedMap(@Nullable LMap attachedMap) {
        this.attachedMap = attachedMap;
    }
}
