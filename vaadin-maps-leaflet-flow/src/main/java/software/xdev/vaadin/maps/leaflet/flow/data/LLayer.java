package software.xdev.vaadin.maps.leaflet.flow.data;

import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.tooltip.LTooltip;

public abstract class LLayer extends LManagedComponent {
    public void toggleTooltip()
    {
        assert this.isAttachedToMap();
        this.getAttachedMap().getElement().executeJs(LMap.GET_ITEM_FUNCTION_CALL + ".toggleTooltip()", this.getMapItemId());
    }

    /**
     * Binds a tooltip to leaflet; Leaflet handles that an old tooltip will be unbound automatically.
     */
    public void bindTooltip(LTooltip tooltip) {
        assert this.isAttachedToMap();
        this.getAttachedMap().getElement().executeJs("this.bindTooltip($0, $1)", this.getMapItemId(), tooltip.toJson());
    }

    public void unbindTooltip() {
        assert this.isAttachedToMap();
        this.getAttachedMap().getElement().executeJs("this.unbindTooltip($0)", this.getMapItemId());
    }
}
