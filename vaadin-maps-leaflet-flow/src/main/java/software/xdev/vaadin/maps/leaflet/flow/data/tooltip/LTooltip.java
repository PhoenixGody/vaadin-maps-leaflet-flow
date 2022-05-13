package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LTooltip implements CanConvertToJson {
    private String tooltipContent;
    private LLayer componentToBindOn;
    private LTooltipOptions tooltipOptions;

    public LTooltip(String tooltipContent, LLayer componentToBindOn, LTooltipOptions tooltipOptions) {
        super();
        this.tooltipContent = tooltipContent;
        this.componentToBindOn = componentToBindOn;
        this.tooltipOptions = tooltipOptions;
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("tooltipContent", tooltipContent);
        result.put("bindingLayerItemId", componentToBindOn.getMapItemId());
        result.put("tooltipOptions", tooltipOptions.toJson());
        return result;
    }
}
