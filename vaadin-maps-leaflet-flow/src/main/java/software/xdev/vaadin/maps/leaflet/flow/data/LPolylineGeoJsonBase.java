package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public abstract class LPolylineGeoJsonBase extends LPolylineBase {
    final GeoJsonCoordinatesContainer geoJsonCoordinatesContainer;

    public LPolylineGeoJsonBase(@NotNull String geoJsonCoordsSerialized, int levelsDeep, @NotNull LPolylineOptions options) {
        super(options);
        geoJsonCoordinatesContainer = new GeoJsonCoordinatesContainer(geoJsonCoordsSerialized, levelsDeep);
    }

    public LPolylineGeoJsonBase(@NotNull JsonValue geoJsonCoordsObject, int levelsDeep, @NotNull LPolylineOptions options) {
        super(options);
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
