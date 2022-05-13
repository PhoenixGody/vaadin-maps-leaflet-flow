package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

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

    @Nullable
    public LPoint getOffset() {
        return offset;
    }

    public void setOffset(@Nullable LPoint offset) {
        this.offset = offset;
    }

    @Nullable
    public String getDirection() {
        return direction;
    }

    public void setDirection(@Nullable String direction) {
        this.direction = direction;
    }

    @Nullable
    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(@Nullable Boolean permanent) {
        this.permanent = permanent;
    }

    @Nullable
    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(@Nullable Boolean sticky) {
        this.sticky = sticky;
    }

    @Nullable
    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(@Nullable Double opacity) {
        this.opacity = opacity;
    }
}
