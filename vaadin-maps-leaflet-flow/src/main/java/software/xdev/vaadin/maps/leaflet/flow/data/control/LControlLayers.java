package software.xdev.vaadin.maps.leaflet.flow.data.control;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;

public class LControlLayers extends LManagedComponent {
    private final LControlLayersOptions options;
    private final LControlLayersBaseConfig baseConfig;

    public LControlLayers(@NotNull LControlLayersOptions options, @NotNull LControlLayersBaseConfig baseConfig) {
        super();
        this.options = options;
        this.baseConfig = baseConfig;
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("options", options.toJson());
        result.put("baseConfig", baseConfig.toJson());
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
