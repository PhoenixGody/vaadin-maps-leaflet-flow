package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public class LMultiPolygonGeoJson extends LPolygonGeoJsonBase {
    private static final int levelsDeep = 2;

    public LMultiPolygonGeoJson(@NotNull String geoJsonCoordsSerialized) {
        super(geoJsonCoordsSerialized, levelsDeep);
    }

    public LMultiPolygonGeoJson(@NotNull JsonValue geoJsonCoordsObject) {
        super(geoJsonCoordsObject, levelsDeep);
    }
}
