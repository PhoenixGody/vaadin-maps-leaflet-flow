package software.xdev.vaadin.maps.leaflet.flow.data.options;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

/**
 * Shared functionality of all Leaflet option interfaces.
 * This interface should be part of all possible options interfaces (like InteractiveOptions, PathOptions, etc.)
 * while the {@link LeafletOptionsBase} is the parent class for a specific Leaflet Option Class like, for example the PolylineOptions.
 *
 * This is due to the fact that in the Leaflet-JS-library the options are extended by several classes the same time.
 */
public interface LeafletOptionsShared extends CanConvertToJson, HasLeafletOptionsContainer {
    @Override
    default JsonValue toJson() {
        JsonObject result = Json.createObject();
        getLeafletOptionsContainer().forEach((leafletOptionsContainerKey, value) -> {
            if (value != null) {
                result.put(leafletOptionsContainerKey.getName(), _convertValue(value));
            }

        });
        return result;
    }

    private JsonValue _convertValue(@NotNull Object value)
    {
        JsonValue result;
        //todo: custom converter
        if (value instanceof Boolean)
            result = Json.create((Boolean) value);
        else if (value instanceof String)
            result = Json.create((String) value);
        else if (value instanceof Double)
            result = Json.create((Double) value);
        else if (value instanceof Integer)
            result = Json.create((Integer) value);
        else if (value instanceof JsonValue)
            result = (JsonValue) value;
        else
            throw new IllegalArgumentException("unconvertable type given");

        return result;
    }
}