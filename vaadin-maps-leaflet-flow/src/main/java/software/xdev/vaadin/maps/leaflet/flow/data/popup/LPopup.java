package software.xdev.vaadin.maps.leaflet.flow.data.popup;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.LLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LPopup implements CanConvertToJson {
    private String popupContent;
    private LPopupOptions popupOptions;
    private LPopupExtras popupExtras;

    public LPopup(String tooltipContent, LPopupOptions popupOptions, LPopupExtras popupExtras) {
        super();
        this.popupContent = tooltipContent;
        this.popupOptions = popupOptions;
        this.popupExtras = popupExtras;
        if (this.popupOptions.getAutoClose() == null && popupExtras.getOpenOnAdd())
            this.popupOptions.setAutoClose(false);
    }

    public LPopup(String tooltipContent, LPopupOptions popupOptions) {
        this(tooltipContent, popupOptions, LPopupExtras.create());
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("popupContent", popupContent);
        result.put("popupOptions", popupOptions.toJson());
        result.put("popupExtras", popupExtras.toJson());
        return result;
    }
}
