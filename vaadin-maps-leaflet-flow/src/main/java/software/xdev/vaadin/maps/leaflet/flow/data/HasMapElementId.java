package software.xdev.vaadin.maps.leaflet.flow.data;

/**
 * Interface for specifying that an object has an elementId for identifying the element in, for example, listeners.
 */
public interface HasMapElementId {
    void setMapElementId(String mapElementId);

    String getMapElementId();
}
