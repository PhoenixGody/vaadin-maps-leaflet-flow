package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public class LMultiPolylineGeoJson extends LPolylineGeoJsonBase {
    private static int LEVELS_DEEP = 1;
    public LMultiPolylineGeoJson(@NotNull String geoJsonCoordsSerialized, @NotNull LPolylineOptions options) {
        super(geoJsonCoordsSerialized, LEVELS_DEEP, options);
    }

    public LMultiPolylineGeoJson(@NotNull JsonValue geoJsonCoordsObject, @NotNull LPolylineOptions options) {
        super(geoJsonCoordsObject, LEVELS_DEEP, options);
    }
}
