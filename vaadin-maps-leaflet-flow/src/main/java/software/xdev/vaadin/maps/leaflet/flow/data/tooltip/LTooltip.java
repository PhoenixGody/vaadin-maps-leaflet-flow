package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.LLayer;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LTooltip implements CanConvertToJson {
    private final LTooltipExtras tooltipExtras;
    private String tooltipContent;
    private LTooltipOptions tooltipOptions;

    public LTooltip(String tooltipContent, LTooltipOptions tooltipOptions, LTooltipExtras tooltipExtras) {
        super();
        this.tooltipContent = tooltipContent;
        this.tooltipOptions = tooltipOptions;
        this.tooltipExtras = tooltipExtras;
    }

    public LTooltip(String tooltipContent, LTooltipOptions tooltipOptions) {
        this(tooltipContent, tooltipOptions, LTooltipExtras.create());
    }

    @Override
    public JsonValue toJson() {
        final JsonObject result = Json.createObject();
        result.put("tooltipContent", tooltipContent);
        result.put("tooltipOptions", tooltipOptions.toJson());
        result.put("tooltipExtras", tooltipExtras.toJson());
        return result;
    }
}
