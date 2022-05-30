package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;

public abstract class LPolylineBase extends LLayer {
    protected final LPolylineOptions properties;

    public LPolylineBase(LPolylineOptions properties) {
        this.properties = properties;
    }

    @Override
    public JsonValue toJson()
    {
        JsonObject jsonObject = Json.createObject();
        jsonObject.put("type", Json.create("Feature"));
        jsonObject.put("properties", this.properties.toJson());
        return jsonObject;
    }

    @Override
    public String getJsFunctionForAddingToMap()
    {
        return "addPolyline";
    }
}
