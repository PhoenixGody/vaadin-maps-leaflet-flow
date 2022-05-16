package software.xdev.vaadin.maps.leaflet.flow.data.popup;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.LLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LPopup implements CanConvertToJson {
    private String popupContent;
    private LLayer componentToBindOn;
    private LPopupOptions popupOptions;

    public LPopup(String tooltipContent, LLayer componentToBindOn, LPopupOptions popupOptions) {
        super();
        this.popupContent = tooltipContent;
        this.componentToBindOn = componentToBindOn;
        this.popupOptions = popupOptions;
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("popupContent", popupContent);
        result.put("bindingLayerItemId", componentToBindOn.getMapItemId());
        result.put("popupOptions", popupOptions.toJson());
        return result;
    }
}
