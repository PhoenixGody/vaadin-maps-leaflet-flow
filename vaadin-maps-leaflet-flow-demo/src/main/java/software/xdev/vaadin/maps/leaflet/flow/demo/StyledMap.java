package software.xdev.vaadin.maps.leaflet.flow.demo;

import com.vaadin.flow.component.dependency.CssImport;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;

/**
 * Example for a Map that is custom styleable
 */
@CssImport(value = "./styles/customStyle.css", themeFor = "leaflet-map")
public class StyledMap extends LMap {
    public StyledMap(double lat, double lon, int zoom, @Nullable LTileLayer tileLayer) {
        super(lat, lon, zoom, tileLayer);
    }
}
