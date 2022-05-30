package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public class LPolylineGeoJson extends LPolylineGeoJsonBase {
    private static int LEVELS_DEEP = 0;
    public LPolylineGeoJson(@NotNull String geoJsonCoordsSerialized, @NotNull LPolylineOptions options) {
        super(geoJsonCoordsSerialized, LEVELS_DEEP, options);
    }

    public LPolylineGeoJson(@NotNull JsonValue geoJsonCoordsObject, @NotNull LPolylineOptions options) {
        super(geoJsonCoordsObject, LEVELS_DEEP, options);
    }
}
