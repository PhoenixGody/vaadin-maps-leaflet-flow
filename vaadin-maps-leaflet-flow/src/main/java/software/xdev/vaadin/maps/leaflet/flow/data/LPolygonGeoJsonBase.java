package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

/**
 * Leaflet polygon that uses geoJson as information source for coordinates.
 * May accept either a {@link JsonValue} or a string representation of the geoJSON.
 */
public abstract class LPolygonGeoJsonBase extends LPolygonBase {
    final GeoJsonCoordinatesContainer geoJsonCoordinatesContainer;

    protected LPolygonGeoJsonBase(@NotNull String geoJsonCoordsSerialized, int levelsDeep) {
        super();
        geoJsonCoordinatesContainer = new GeoJsonCoordinatesContainer(geoJsonCoordsSerialized, levelsDeep);
    }

    protected LPolygonGeoJsonBase(@NotNull JsonValue geoJsonCoordsObject, int levelsDeep) {
        super();
        geoJsonCoordinatesContainer = new GeoJsonCoordinatesContainer(geoJsonCoordsObject, levelsDeep);
    }

    @Override
    public JsonValue toJson()
    {
        JsonObject result = (JsonObject) super.toJson();
        geoJsonCoordinatesContainer.extendJsonObject(result);
        return result;
    }
}
