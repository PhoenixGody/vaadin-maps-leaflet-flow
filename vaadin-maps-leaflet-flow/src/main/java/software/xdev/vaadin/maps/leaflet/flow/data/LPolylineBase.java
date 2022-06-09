package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public abstract class LPolylineBase extends LLayer {
    protected final LPolylineOptions options;

    public LPolylineBase(LPolylineOptions options) {
        super();
        this.options = options;
    }

    @NotNull
    public LPolylineOptions getOptions() {
        return options;
    }

    @Override
    public JsonValue toJson()
    {
        JsonObject jsonObject = Json.createObject();
        jsonObject.put("type", Json.create("Feature"));
        jsonObject.put("properties", this.options.toJson());
        return jsonObject;
    }

    @Override
    public String getJsFunctionForAddingToMap()
    {
        return "addPolyline";
    }
}
