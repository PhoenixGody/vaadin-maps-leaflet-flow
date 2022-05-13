package software.xdev.vaadin.maps.leaflet.flow.data.control;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

import java.util.HashMap;
import java.util.Map;

public class LControlLayersBaseConfig implements CanConvertToJson {
    private Map<String, LTileLayer> tileLayers = new HashMap<>();

    public void addTileLayer(@NotNull String displayName, @NotNull LTileLayer tileLayer){
        //todo seal after push to server
        tileLayers.put(displayName, tileLayer);
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        tileLayers.forEach((displayName, tileLayer) -> {
            result.put(displayName, tileLayer.getMapItemId());
        });
        return result;
    }
}
