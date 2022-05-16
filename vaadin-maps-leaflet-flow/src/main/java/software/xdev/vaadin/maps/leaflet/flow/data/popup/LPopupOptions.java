package software.xdev.vaadin.maps.leaflet.flow.data.popup;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.internal.JsonUtils;
import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

public class LPopupOptions implements CanConvertToJson {
    private LPoint offset;
    private Integer maxWidth;
    private Integer minWidth;
    private Integer maxHeight;
    private Boolean autoPan;
    private LPoint autoPanPaddingTopLeft;
    private LPoint autoPanPaddingBottomRight;
    private LPoint autoPanPadding;
    private Boolean keepInView;
    private Boolean closeButton;
    private Boolean autoClose;
    private Boolean closeOnClick;
    private String className;

    public LPopupOptions() {
    }



    @Override
    @NotNull
    public JsonValue toJson() {
        JsonObject result = Json.createObject();

        if(getOffset() != null) {
            JsonValue offsetPoint = getOffset().toJson();
            if (offsetPoint != null)
                result.put("offset", offsetPoint);
        }

        String res = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {

            res = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        result = JsonUtil.parse(res);//todo serializing and then deserializing could be avoided


        return result;
    }

    // region getter

    @Nullable
    public LPoint getOffset() {
        return offset;
    }

    @Nullable
    public Integer getMaxWidth() {
        return maxWidth;
    }

    @Nullable
    public Integer getMinWidth() {
        return minWidth;
    }

    @Nullable
    public Integer getMaxHeight() {
        return maxHeight;
    }

    @Nullable
    public Boolean getAutoPan() {
        return autoPan;
    }

    @Nullable
    public LPoint getAutoPanPaddingTopLeft() {
        return autoPanPaddingTopLeft;
    }

    @Nullable
    public LPoint getAutoPanPaddingBottomRight() {
        return autoPanPaddingBottomRight;
    }

    @Nullable
    public LPoint getAutoPanPadding() {
        return autoPanPadding;
    }

    @Nullable
    public Boolean getKeepInView() {
        return keepInView;
    }

    @Nullable
    public Boolean getCloseButton() {
        return closeButton;
    }

    @Nullable
    public Boolean getAutoClose() {
        return autoClose;
    }

    @Nullable
    public Boolean getCloseOnClick() {
        return closeOnClick;
    }

    @Nullable
    public String getClassName() {
        return className;
    }
    // endregion

    // region setter

    public void setOffset(@Nullable LPoint offset) {
        this.offset = offset;
    }

    public void setMaxWidth(@Nullable Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMinWidth(@Nullable Integer minWidth) {
        this.minWidth = minWidth;
    }

    public void setMaxHeight(@Nullable Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setAutoPan(@Nullable Boolean autoPan) {
        this.autoPan = autoPan;
    }

    public void setAutoPanPaddingTopLeft(@Nullable LPoint autoPanPaddingTopLeft) {
        this.autoPanPaddingTopLeft = autoPanPaddingTopLeft;
    }

    public void setAutoPanPaddingBottomRight(@Nullable LPoint autoPanPaddingBottomRight) {
        this.autoPanPaddingBottomRight = autoPanPaddingBottomRight;
    }

    public void setAutoPanPadding(@Nullable LPoint autoPanPadding) {
        this.autoPanPadding = autoPanPadding;
    }

    public void setKeepInView(@Nullable Boolean keepInView) {
        this.keepInView = keepInView;
    }

    public void setCloseButton(@Nullable Boolean closeButton) {
        this.closeButton = closeButton;
    }

    public void setAutoClose(@Nullable Boolean autoClose) {
        this.autoClose = autoClose;
    }

    public void setCloseOnClick(@Nullable Boolean closeOnClick) {
        this.closeOnClick = closeOnClick;
    }

    public void setClassName(@Nullable String className) {
        this.className = className;
    }

    // endregion
}
