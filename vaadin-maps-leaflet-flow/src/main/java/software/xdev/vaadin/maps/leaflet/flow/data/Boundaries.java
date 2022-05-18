package software.xdev.vaadin.maps.leaflet.flow.data;

/**
 * A complete boundaryset of a rectangular geographical area.
 * This means that not only two corners are defined but all 4 corners.
 *
 * No validation is done for integrity of the given data.
 *
 * This object has no corresponding equivalent in Leaflet but is used for providing data from events for example.
 */
public class Boundaries {
    private LLatLng northEast;
    private LLatLng northWest;
    private LLatLng southEast;
    private LLatLng southWest;

    public LLatLng getNorthEast() {
        return northEast;
    }

    protected void setNorthEast(LLatLng northEast) {
        this.northEast = northEast;
    }

    public LLatLng getNorthWest() {
        return northWest;
    }

    protected void setNorthWest(LLatLng northWest) {
        this.northWest = northWest;
    }

    public LLatLng getSouthEast() {
        return southEast;
    }

    protected void setSouthEast(LLatLng southEast) {
        this.southEast = southEast;
    }

    public LLatLng getSouthWest() {
        return southWest;
    }

    protected void setSouthWest(LLatLng southWest) {
        this.southWest = southWest;
    }

    public Boundaries(LLatLng northEast, LLatLng northWest, LLatLng southEast, LLatLng southWest) {
        setNorthEast(northEast);
        setNorthWest(northWest);
        setSouthEast(southEast);
        setSouthWest(southWest);
    }

    public static Boundaries of(double northEastLat, double northEastLng,
                                double northWestLat, double northWestLng,
                                double southEastLat, double southEastLng,
                                double southWestLat, double southWestLng)
    {
        LLatLng northEastCoordinates = new LLatLng(northEastLat, northEastLng);
        LLatLng northWestCoordinates = new LLatLng(northWestLat, northWestLng);
        LLatLng southEastCoordinates = new LLatLng(southEastLat, southEastLng);
        LLatLng southWestCoordinates = new LLatLng(southWestLat, southWestLng);
        return new Boundaries(northEastCoordinates, northWestCoordinates, southEastCoordinates, southWestCoordinates);
    }
}
