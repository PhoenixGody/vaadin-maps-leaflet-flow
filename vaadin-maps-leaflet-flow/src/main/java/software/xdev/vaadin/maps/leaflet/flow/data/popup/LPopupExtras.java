package software.xdev.vaadin.maps.leaflet.flow.data.popup;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

/**
 * Extra settings for a popup, which are handled in the vaadin connector.
 */
public class LPopupExtras implements CanConvertToJson {

    private boolean openOnAdd;
    private boolean openOnMouseIn;
    private boolean closeOnMouseOut;

    public LPopupExtras() {
        openOnAdd = false;
        openOnMouseIn = false;
        closeOnMouseOut = false;
    }

    public LPopupExtras(boolean openOnAdd) {
        this.openOnAdd = openOnAdd;
    }

    public static LPopupExtras create() {
        return new LPopupExtras();
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("openOnAdd", getOpenOnAdd());
        result.put("openOnMouseIn", getOpenOnMouseIn());
        result.put("closeOnMouseOut", getCloseOnMouseOut());
        return result;
    }

    // region setters

    public LPopupExtras setOpenOnAdd(boolean openOnAdd) {
        this.openOnAdd = openOnAdd;
        return this;
    }

    public LPopupExtras setOpenOnMouseIn(boolean openOnMouseIn) {
        this.openOnMouseIn = openOnMouseIn;
        return this;
    }

    public LPopupExtras setCloseOnMouseOut(boolean closeOnMouseOut) {
        this.closeOnMouseOut = closeOnMouseOut;
        return this;
    }

    // endregion

    // region getters
    public boolean getOpenOnAdd() {
        return openOnAdd;
    }

    public boolean getOpenOnMouseIn() {
        return openOnMouseIn;
    }

    public boolean getCloseOnMouseOut() {
        return closeOnMouseOut;
    }

    // endregion
}
