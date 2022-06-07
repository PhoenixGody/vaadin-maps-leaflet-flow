package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LMarkerExtras implements CanConvertToJson {
    private LIcon icon;

    public LMarkerExtras() {
        this.icon = new LIcon();
    }

    @NotNull
    public LIcon getIcon()
    {
        return this.icon;
    }

    public void setIcon(final @NotNull LIcon icon)
    {
        this.icon = icon;
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("icon", getIcon().toJson());
        return result;
    }
}
