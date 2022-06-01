package software.xdev.vaadin.maps.leaflet.flow.data.options;

import org.jetbrains.annotations.Nullable;


/**
 * Convenience wrapper for all the options of Leaflets "Path"-class:
 * https://leafletjs.com/reference.html#path
 * No validation is performed, the values are passed 1:1 to the clientside.
 */
public interface HasPathOptions extends LeafletOptionsShared {
    LeafletOptionsContainerKey<Boolean> STROKE = new LeafletOptionsContainerKey<>(Boolean.class, "stroke");
    LeafletOptionsContainerKey<String> COLOR = new LeafletOptionsContainerKey<>(String.class, "color");
    LeafletOptionsContainerKey<Integer> WEIGHT = new LeafletOptionsContainerKey<>(Integer.class, "weight");
    LeafletOptionsContainerKey<Double> OPACITY = new LeafletOptionsContainerKey<>(Double.class, "opacity");
    LeafletOptionsContainerKey<String> LINE_CAP = new LeafletOptionsContainerKey<>(String.class, "lineCap");
    LeafletOptionsContainerKey<String> LINE_JOIN = new LeafletOptionsContainerKey<>(String.class, "lineJoin");
    LeafletOptionsContainerKey<String> DASH_ARRAY = new LeafletOptionsContainerKey<>(String.class, "dashArray");
    LeafletOptionsContainerKey<String> DASH_OFFSET = new LeafletOptionsContainerKey<>(String.class, "dashOffset");
    LeafletOptionsContainerKey<Boolean> FILL = new LeafletOptionsContainerKey<>(Boolean.class, "fill");
    LeafletOptionsContainerKey<String> FILL_COLOR = new LeafletOptionsContainerKey<>(String.class, "fillColor");
    LeafletOptionsContainerKey<Double> FILL_OPACITY = new LeafletOptionsContainerKey<>(Double.class, "fillOpacity");
    LeafletOptionsContainerKey<String> FILL_RULE = new LeafletOptionsContainerKey<>(String.class, "fillRule");
    LeafletOptionsContainerKey<Boolean> BUBBLING_MOUSE_EVENTS = new LeafletOptionsContainerKey<>(Boolean.class, "bubblingMouseEvents");
    LeafletOptionsContainerKey<String> CLASS_NAME = new LeafletOptionsContainerKey<>(String.class, "className");

    default void setStroke(Boolean value) {
        getLeafletOptionsContainer().putTyped(STROKE, value);
    }

    default @Nullable Boolean getStroke() {
        return getLeafletOptionsContainer().getTyped(STROKE);
    }

    default void setColor(String value) {
        getLeafletOptionsContainer().putTyped(COLOR, value);
    }

    default @Nullable String getColor() {
        return getLeafletOptionsContainer().getTyped(COLOR);
    }

    default void setWeight(Integer value) {
        getLeafletOptionsContainer().putTyped(WEIGHT, value);
    }

    default @Nullable Integer getWeight() {
        return getLeafletOptionsContainer().getTyped(WEIGHT);
    }


    default void setOpacity(Double value) {
        getLeafletOptionsContainer().putTyped(OPACITY, value);
    }

    default @Nullable Double getOpacity() {
        return getLeafletOptionsContainer().getTyped(OPACITY);
    }

    default void setLineCap(String value) {
        getLeafletOptionsContainer().putTyped(LINE_CAP, value);
    }

    default @Nullable String getLineCap() {
        return getLeafletOptionsContainer().getTyped(LINE_CAP);
    }

    default void setLineJoin(String value) {
        getLeafletOptionsContainer().putTyped(LINE_JOIN, value);
    }

    default @Nullable String getLineJoin() {
        return getLeafletOptionsContainer().getTyped(LINE_JOIN);
    }

    default void setDashArray(String value) {
        getLeafletOptionsContainer().putTyped(DASH_ARRAY, value);
    }

    default @Nullable String getDashArray() {
        return getLeafletOptionsContainer().getTyped(DASH_ARRAY);
    }

    default void setDashOffset(String value) {
        getLeafletOptionsContainer().putTyped(DASH_OFFSET, value);
    }

    default @Nullable String getDashOffset() {
        return getLeafletOptionsContainer().getTyped(DASH_OFFSET);
    }

    default void setFill(Boolean value) {
        getLeafletOptionsContainer().putTyped(FILL, value);
    }

    default @Nullable Boolean getFill() {
        return getLeafletOptionsContainer().getTyped(FILL);
    }

    default void setFillColor(String value) {
        getLeafletOptionsContainer().putTyped(FILL_COLOR, value);
    }

    default @Nullable String getFillColor() {
        return getLeafletOptionsContainer().getTyped(FILL_COLOR);
    }

    default void setFillOpacity(Double value) {
        getLeafletOptionsContainer().putTyped(FILL_OPACITY, value);
    }

    default @Nullable Double getFillOpacity() {
        return getLeafletOptionsContainer().getTyped(FILL_OPACITY);
    }

    default void setFillRule(String value) {
        getLeafletOptionsContainer().putTyped(FILL_RULE, value);
    }

    default @Nullable String getFillRule() {
        return getLeafletOptionsContainer().getTyped(FILL_RULE);
    }

    default void setBubblingMouseEvents(Boolean value) {
        getLeafletOptionsContainer().putTyped(BUBBLING_MOUSE_EVENTS, value);
    }

    default @Nullable Boolean getBubblingMouseEvents() {
        return getLeafletOptionsContainer().getTyped(BUBBLING_MOUSE_EVENTS);
    }

    default void setClassName(String value) {
        getLeafletOptionsContainer().putTyped(CLASS_NAME, value);
    }

    default @Nullable String getClassName() {
        return getLeafletOptionsContainer().getTyped(CLASS_NAME);
    }

}
