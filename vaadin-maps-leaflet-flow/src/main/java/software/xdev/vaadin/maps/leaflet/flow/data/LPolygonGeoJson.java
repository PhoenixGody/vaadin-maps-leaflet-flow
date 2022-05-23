package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public class LPolygonGeoJson extends LPolygonGeoJsonBase {
    private static final int levelsDeep = 1;

    public LPolygonGeoJson(@NotNull String geoJsonCoordsSerialized) {
        super(geoJsonCoordsSerialized, levelsDeep);
    }

    public LPolygonGeoJson(@NotNull JsonValue geoJsonCoordsObject) {
        super(geoJsonCoordsObject, levelsDeep);
    }
}
