package software.xdev.vaadin.maps.leaflet.flow.data.control;


import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

/**
 * See the official leaflet documentation for these properties:
 * <a href="https://leafletjs.com/reference.html#control">https://leafletjs.com/reference.html#control</a>
 */
public class LControlLayersOptions implements CanConvertToJson {
    private Boolean collapsed;
    private Boolean autoZIndex;

    /**
     * To prevent misunderstanding of this property in leaflet: this will hide only the element to choose the base-layer.
     * It will not hide the complete Control.layers-element.
     * If you've got only one BaseLayer and have nothing else and do not want to see the Control it's best to
     * not add the control-element.
     */
    private Boolean hideSingleBase;
    private Boolean sortLayers;
    public LControlLayersOptions(@Nullable Boolean collapsed, @Nullable Boolean autoZIndex, @Nullable Boolean hideSingleBase, @Nullable Boolean sortLayers) {
        setCollapsed(collapsed);
        setAutoZIndex(autoZIndex);
        setHideSingleBase(hideSingleBase);
        setSortLayers(sortLayers);
    }

    @Nullable
    public Boolean getCollapsed() {
        return collapsed;
    }

    private void setCollapsed(@Nullable Boolean collapsed) {
        this.collapsed = collapsed;
    }

    @Nullable
    public Boolean getAutoZIndex() {
        return autoZIndex;
    }

    private void setAutoZIndex(@Nullable Boolean autoZIndex) {
        this.autoZIndex = autoZIndex;
    }

    @Nullable
    public Boolean getHideSingleBase() {
        return hideSingleBase;
    }

    private void setHideSingleBase(@Nullable Boolean hideSingleBase) {
        this.hideSingleBase = hideSingleBase;
    }

    @Nullable
    public Boolean getSortLayers() {
        return sortLayers;
    }

    private void setSortLayers(@Nullable Boolean sortLayers) {
        this.sortLayers = sortLayers;
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        if (getCollapsed() != null)
            result.put("collapsed", getCollapsed());
        if (getAutoZIndex() != null)
            result.put("autoZIndex", getAutoZIndex());
        if (getHideSingleBase() != null)
            result.put("hideSingleBase", getHideSingleBase());
        if (getSortLayers() != null)
            result.put("sortLayers", getSortLayers());
        return result;
    }
}
