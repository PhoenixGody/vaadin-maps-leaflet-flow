package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;


/**
 * official Leaflet JS Options for Tooltips
 */
public class LTooltipOptions implements CanConvertToJson {
    private LPoint offset;
    private String direction;
    private Boolean permanent;
    private Boolean sticky;
    private Double opacity;

    public LTooltipOptions(@Nullable LPoint offset,
                           @Nullable String direction,
                           @Nullable Boolean permanent,
                           @Nullable Boolean sticky,
                           @Nullable Double opacity) {
        setOffset(offset);
        setDirection(direction);
        setPermanent(permanent);
        setSticky(sticky);
        setOpacity(opacity);
    }

    public LTooltipOptions() {
    }

    public static LTooltipOptions create(){
        return new LTooltipOptions();
    }

    @Override
    @NotNull
    public JsonValue toJson() {
        JsonObject result = Json.createObject();
        if(getOffset() != null)
        {
            JsonValue offsetPoint = getOffset().toJson();
            if (offsetPoint != null)
                result.put("offset", offsetPoint);
        }

        if (getDirection() != null)
            result.put("direction", getDirection());
        if (getPermanent() != null)
            result.put("permanent", getPermanent());
        if (getSticky() != null)
            result.put("sticky", getSticky());
        if (getOpacity() != null)
            result.put("opacity", getOpacity());
        return result;
    }

    // region getters
    @Nullable
    public LPoint getOffset() {
        return offset;
    }

    @Nullable
    public String getDirection() {
        return direction;
    }

    @Nullable
    public Boolean getPermanent() {
        return permanent;
    }

    @Nullable
    public Boolean getSticky() {
        return sticky;
    }
    @Nullable
    public Double getOpacity() {
        return opacity;
    }
    // endregion getters

    // region setters
    public LTooltipOptions setOffset(@Nullable LPoint offset) {
        this.offset = offset;
        return this;
    }

    public LTooltipOptions setDirection(@Nullable String direction) {
        this.direction = direction;
        return this;
    }

    public LTooltipOptions setPermanent(@Nullable Boolean permanent) {
        this.permanent = permanent;
        return this;
    }

    public LTooltipOptions setSticky(@Nullable Boolean sticky) {
        this.sticky = sticky;
        return this;
    }

    public LTooltipOptions setOpacity(@Nullable Double opacity) {
        this.opacity = opacity;
        return this;
    }
    //endregion setters

}
