package software.xdev.vaadin.maps.leaflet.flow.data;

/**
 * geographical point with latitude and longitude
 */
public class LLatLng {
    double lat;
    double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LLatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
