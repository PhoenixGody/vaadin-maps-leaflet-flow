package software.xdev.vaadin.maps.leaflet.flow.event;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.LMap;

@DomEvent("map-leaflet-locationerror")
public class LocationErrorEvent extends ComponentEvent<LMap> {

    private String message;
    private Double code;

    public LocationErrorEvent(LMap source,
                              boolean fromClient,
                              @EventData("event.detail.message") String message,
                              @EventData("event.detail.code") Double code
                              ) {
        super(source, fromClient);
        this.message = message;
        this.code = code;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public Double getCode() {
        return code;
    }
}
