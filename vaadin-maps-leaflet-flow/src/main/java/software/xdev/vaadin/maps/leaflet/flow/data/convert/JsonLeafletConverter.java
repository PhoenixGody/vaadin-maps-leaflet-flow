package software.xdev.vaadin.maps.leaflet.flow.data.convert;

import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;

/**
 * Utility functions to convert vaadins JsonValues and the serverside Leaflet objects.
 */
public class JsonLeafletConverter {
    private JsonLeafletConverter() {
        //no instance
    }

    public static LLatLng jsonValue2LLatLng(JsonValue position)
    {
        assert position instanceof JsonObject;//todo
        JsonObject latLng = (JsonObject) position;

        double lat = latLng.getNumber("lat");
        double lng = latLng.getNumber("lng");
        return new LLatLng(lat, lng);
    }
}
