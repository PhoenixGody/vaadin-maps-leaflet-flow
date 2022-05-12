package software.xdev.vaadin.maps.leaflet.flow.data.control;

import com.fasterxml.jackson.core.JsonFactory;
import elemental.json.Json;
import elemental.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LControlLayersBaseConfig implements CanConvertToJson {
    private Map<String, LTileLayer> tileLayers = new HashMap<>();

    public void addTileLayer(@NotNull String displayName, @NotNull LTileLayer tileLayer){
        //todo seal after push to server
        tileLayers.put(displayName, tileLayer);
    }

    @Override
    public JsonObject toJson() {
        final JsonObject result = Json.createObject();
        tileLayers.forEach((displayName, tileLayer) -> {
            result.put(displayName, tileLayer.getMapItemId());
        });
        return result;
    }
}
