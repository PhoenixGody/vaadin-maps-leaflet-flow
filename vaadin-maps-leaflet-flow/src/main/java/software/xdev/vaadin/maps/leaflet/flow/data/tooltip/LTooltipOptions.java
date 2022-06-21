package software.xdev.vaadin.maps.leaflet.flow.data.tooltip;

import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.LPoint;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasClassNameOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasInteractiveOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;


/**
 * official Leaflet JS Options for Tooltips
 */
public class LTooltipOptions extends LeafletOptionsBase implements HasInteractiveOptions, HasClassNameOptions<LTooltipOptions> {

    LeafletOptionsContainerKey<LPoint> OFFSET = new LeafletOptionsContainerKey<>(LPoint.class, "offset");
    LeafletOptionsContainerKey<String> DIRECTION = new LeafletOptionsContainerKey<>(String.class, "direction");
    LeafletOptionsContainerKey<Boolean> PERMANENT = new LeafletOptionsContainerKey<>(Boolean.class, "permanent");
    LeafletOptionsContainerKey<Boolean> STICKY = new LeafletOptionsContainerKey<>(Boolean.class, "sticky");
    LeafletOptionsContainerKey<Double> OPACITY = new LeafletOptionsContainerKey<>(Double.class, "opacity");

    public LTooltipOptions(@Nullable LPoint offset,
                           @Nullable String direction,
                           @Nullable Boolean permanent,
                           @Nullable Boolean sticky,
                           @Nullable Double opacity,
                           @Nullable String className) {
        setOffset(offset);
        setDirection(direction);
        setPermanent(permanent);
        setSticky(sticky);
        setOpacity(opacity);
        setClassName(className);
    }

    public LTooltipOptions() {
    }

    public static LTooltipOptions create(){
        return new LTooltipOptions();
    }

    // region getters
    @Nullable
    public LPoint getOffset() {
        return getLeafletOptionsContainer().getTyped(OFFSET);
    }

    @Nullable
    public String getDirection() {
        return getLeafletOptionsContainer().getTyped(DIRECTION);
    }

    @Nullable
    public Boolean getPermanent() {
        return getLeafletOptionsContainer().getTyped(PERMANENT);
    }

    @Nullable
    public Boolean getSticky() {
        return getLeafletOptionsContainer().getTyped(STICKY);
    }
    @Nullable
    public Double getOpacity() {
        return getLeafletOptionsContainer().getTyped(OPACITY);
    }
    // endregion getters

    // region setters
    public LTooltipOptions setOffset(@Nullable LPoint offset) {
        getLeafletOptionsContainer().putTyped(OFFSET, offset);
        return this;
    }

    public LTooltipOptions setDirection(@Nullable String direction) {
        getLeafletOptionsContainer().putTyped(DIRECTION, direction);
        return this;
    }

    public LTooltipOptions setPermanent(@Nullable Boolean permanent) {
        getLeafletOptionsContainer().putTyped(PERMANENT, permanent);
        return this;
    }

    public LTooltipOptions setSticky(@Nullable Boolean sticky) {
        getLeafletOptionsContainer().putTyped(STICKY, sticky);
        return this;
    }

    public LTooltipOptions setOpacity(@Nullable Double opacity) {
        getLeafletOptionsContainer().putTyped(OPACITY, opacity);
        return this;
    }
    //endregion setters

}
