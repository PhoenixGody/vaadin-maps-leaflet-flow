package software.xdev.vaadin.maps.leaflet.flow.data;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LLocateOptions implements CanConvertToJson {
    private Boolean watch;
    private Boolean setView;
    private Integer maxZoom;
    private Integer timeout;
    private Integer maximumAge;
    private Boolean enableHighAccuracy;

    public LLocateOptions() {
    }

    public LLocateOptions(@Nullable Boolean watch, @Nullable Boolean setView, @Nullable Integer maxZoom, @Nullable Integer timeout, @Nullable Integer maximumAge, @Nullable Boolean enableHighAccuracy) {
        this.watch = watch;
        this.setView = setView;
        this.maxZoom = maxZoom;
        this.timeout = timeout;
        this.maximumAge = maximumAge;
        this.enableHighAccuracy = enableHighAccuracy;
    }

    @Override
    public JsonValue toJson() {
        JsonObject result = Json.createObject();
        if (getWatch() != null)
            result.put("watch", getWatch());
        if (getSetView() != null)
            result.put("setView", getSetView());
        if (getMaxZoom() != null)
            result.put("maxZoom", getMaxZoom());
        if (getTimeout() != null)
            result.put("timeout", getTimeout());
        if (getMaximumAge() != null)
            result.put("maximumAge", getMaximumAge());
        if (getEnableHighAccuracy() != null)
            result.put("enableHighAccuracy", getEnableHighAccuracy());
        return result;
    }

    // region getter
    @Nullable
    public Boolean getWatch() {
        return watch;
    }

    @Nullable
    public Boolean getSetView() {
        return setView;
    }

    @Nullable
    public Integer getMaxZoom() {
        return maxZoom;
    }

    @Nullable
    public Integer getTimeout() {
        return timeout;
    }

    @Nullable
    public Integer getMaximumAge() {
        return maximumAge;
    }

    @Nullable
    public Boolean getEnableHighAccuracy() {
        return enableHighAccuracy;
    }
    // endregion

}
