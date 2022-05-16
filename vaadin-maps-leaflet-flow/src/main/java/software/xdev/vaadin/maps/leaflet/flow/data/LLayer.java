package software.xdev.vaadin.maps.leaflet.flow.data;

import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.popup.LPopup;
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

    /**
     * Binds a popup to leaflet; Leaflet handles that an old popup will be unbound automatically.
     */
    public void bindPopup(LPopup popup) {
        assert this.isAttachedToMap();
        this.getAttachedMap().getElement().executeJs("this.bindPopup($0, $1)", this.getMapItemId(), popup.toJson());
    }

    public void unbindPopup() {
        assert this.isAttachedToMap();
        this.getAttachedMap().getElement().executeJs("this.unbindPopup($0)", this.getMapItemId());
    }
}
