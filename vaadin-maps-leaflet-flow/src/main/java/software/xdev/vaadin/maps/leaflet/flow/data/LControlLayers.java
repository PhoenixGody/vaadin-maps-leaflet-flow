package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import software.xdev.vaadin.maps.leaflet.flow.data.base.LManagedComponent;

public class LControlLayers extends LManagedComponent {
    private final LControlLayersOptions options;

    public LControlLayers(LControlLayersOptions options) {
        super();
        this.options = options;
    }

    @Override
    public JsonObject toJson() {
        final JsonObject result = Json.createObject();
        result.put("options", options.toJson());
        return result;
    }

    public LControlLayersOptions getOptions() {
        return options;
    }

    @Override
    public String getJsFunctionForAddingToMap() {
        return "addControlLayers";
    }
}
