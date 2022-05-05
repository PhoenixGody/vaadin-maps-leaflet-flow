package software.xdev.vaadin.maps.leaflet.flow.data.convert;

import elemental.json.JsonArray;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;

/**
 * Utility functions to convert vaadins JsonValues and the serverside Leaflet objects.
 */
public class JsonLeafletConverter {
    private JsonLeafletConverter() {
        //no instance
    }

    public static LLatLng jsonValue2LLatLng(JsonValue pPosition)
    {
        assert pPosition instanceof JsonArray;//todo
        JsonArray arrayPosition = (JsonArray) pPosition;

        double lat = arrayPosition.getNumber(0);
        double lng = arrayPosition.getNumber(1);
        return new LLatLng(lat, lng);
    }
}
