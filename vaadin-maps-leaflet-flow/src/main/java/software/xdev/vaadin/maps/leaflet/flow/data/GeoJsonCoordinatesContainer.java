package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;

public class GeoJsonCoordinatesContainer {
    private static final String GEO_JSON_NAMESPACE = "geoJson";
    private static final String GEO_JSON_COORDS_STR = "coords";
    private static final String GEO_JSON_COORDS_OBJ = "coords";
    private static final String GEO_JSON_LEVELS_DEEP = "levelsDeep";
    final String geoJsonCoordsSerialized;
    final int levelsDeep;
    final JsonValue geoJsonCoordsObject;

    public GeoJsonCoordinatesContainer(@NotNull String geoJsonCoordsSerialized, int levelsDeep) {
        this.levelsDeep = levelsDeep;
        this.geoJsonCoordsSerialized = geoJsonCoordsSerialized;
        this.geoJsonCoordsObject = null;
    }

    public GeoJsonCoordinatesContainer(@NotNull JsonValue geoJsonCoordsObject, int levelsDeep) {
        this.levelsDeep = levelsDeep;
        this.geoJsonCoordsObject = geoJsonCoordsObject;
        this.geoJsonCoordsSerialized = null;
    }


    public void extendJsonObject (@NotNull JsonObject toExtend) {
        if (this.geoJsonCoordsSerialized != null)
            extendJsonObjectWithSerialized(toExtend);
        else if (this.geoJsonCoordsObject != null)
            extendJsonObjectWithObject(toExtend);
    }

    private void extendJsonObjectWithSerialized(@NotNull JsonObject toExtend) {
        JsonObject geoJsonNs = ensureNamespaceExists(toExtend);
        if (geoJsonNs.hasKey(GEO_JSON_COORDS_STR))
            throw new IllegalArgumentException("Given JsonObject already contains the key " + GEO_JSON_COORDS_STR);

        geoJsonNs.put(GEO_JSON_COORDS_STR, this.geoJsonCoordsSerialized);
        geoJsonNs.put(GEO_JSON_LEVELS_DEEP, this.levelsDeep);
    }

    private void extendJsonObjectWithObject(@NotNull JsonObject toExtend) {
        JsonObject geoJsonNs = ensureNamespaceExists(toExtend);
        if (toExtend.hasKey(GEO_JSON_COORDS_OBJ))
            throw new IllegalArgumentException("Given JsonObject already contains the key " + GEO_JSON_COORDS_OBJ);

        geoJsonNs.put(GEO_JSON_COORDS_OBJ, this.geoJsonCoordsObject);
        geoJsonNs.put(GEO_JSON_LEVELS_DEEP, this.levelsDeep);
    }

    private JsonObject ensureNamespaceExists(@NotNull JsonObject toExtend)
    {
        JsonObject geoJsonNs;
        if (toExtend.hasKey(GEO_JSON_NAMESPACE)) {
            geoJsonNs = toExtend.getObject(GEO_JSON_NAMESPACE);
        }
        else {
            geoJsonNs = Json.createObject();
            toExtend.put(GEO_JSON_NAMESPACE, geoJsonNs);
        }
        return geoJsonNs;
    }
}
