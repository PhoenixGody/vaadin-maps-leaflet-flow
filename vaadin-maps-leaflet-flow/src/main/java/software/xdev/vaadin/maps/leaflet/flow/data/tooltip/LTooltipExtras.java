package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

/**
 * Extra settings for a tooltip, which are handled in the vaadin connector.
 */
public class LTooltipExtras implements CanConvertToJson {

    private boolean openOnAdd;

    public LTooltipExtras() {
        openOnAdd = false;
    }

    public LTooltipExtras(boolean openOnAdd) {
        this.openOnAdd = openOnAdd;
    }

    public static LTooltipExtras create() {
        return new LTooltipExtras();
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("openOnAdd", getOpenOnAdd());
        return result;
    }

    // region setters

    public LTooltipExtras setOpenOnAdd(boolean openOnAdd) {
        this.openOnAdd = openOnAdd;
        return this;
    }

    // endregion

    // region getters
    public boolean getOpenOnAdd() {
        return openOnAdd;
    }

    // endregion
}
