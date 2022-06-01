package software.xdev.vaadin.maps.leaflet.flow;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.base.LComponent;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class LManagedComponent implements LComponent {
    private String mapItemId;
    private LMap attachedMap;

    private final ArrayDeque<Runnable> onAddQueue;

    @Override
    public String getMapItemId() {
        return mapItemId;
    }

    public LManagedComponent() {
        this(UUID.randomUUID().toString());
    }

    public LManagedComponent(@NotNull String mapItemId) {
        this.mapItemId = mapItemId;
        onAddQueue = new ArrayDeque<>();
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

    @NotNull
    public ArrayDeque<Runnable> getOnAddQueue() {
        return onAddQueue;
    }
}
