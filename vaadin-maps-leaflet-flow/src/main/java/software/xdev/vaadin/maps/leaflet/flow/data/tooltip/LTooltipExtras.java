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
    private boolean autoColliding;

    public LTooltipExtras() {
        this(false, false);
    }

    public LTooltipExtras(boolean openOnAdd) {
        this(openOnAdd, false);
    }

    public LTooltipExtras(boolean openOnAdd, boolean autoColliding) {
        this.openOnAdd = openOnAdd;
        this.autoColliding = autoColliding;
    }

    public static LTooltipExtras create() {
        return new LTooltipExtras();
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("openOnAdd", getOpenOnAdd());
        result.put("autoColliding", getAutoColliding());
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

    public boolean getAutoColliding() {
        return autoColliding;
    }

    /**
     * When true the tooltip tries to detect if it is colliding with other tooltips and hide.
     * This will only work if the tooltip option "permanent" is set to "true".
     */
    public void setAutoColliding( boolean autoColliding) {
        this.autoColliding = autoColliding;
    }


    // endregion
}
