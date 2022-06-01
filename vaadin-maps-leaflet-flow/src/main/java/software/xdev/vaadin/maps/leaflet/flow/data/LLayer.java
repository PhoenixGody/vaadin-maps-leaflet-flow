package software.xdev.vaadin.maps.leaflet.flow.data;

import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.popup.LPopup;
import software.xdev.vaadin.maps.leaflet.flow.data.tooltip.LTooltip;

public abstract class LLayer extends LManagedComponent {

    private Runnable tooltipBind;
    private Runnable popupBind;

    public void toggleTooltip()
    {
        Runnable toggle = () -> {
            this.getAttachedMap().getElement().executeJs(LMap.GET_ITEM_FUNCTION_CALL + ".toggleTooltip()", this.getMapItemId());
        };

        if (this.isAttachedToMap())
            toggle.run();
        else
            getOnAddQueue().add(toggle);

    }

    public void togglePopup()
    {
        Runnable toggle = () -> {
            this.getAttachedMap().getElement().executeJs(LMap.GET_ITEM_FUNCTION_CALL + ".togglePopup()", this.getMapItemId());
        };

        if (this.isAttachedToMap())
            toggle.run();
        else
            getOnAddQueue().add(toggle);
    }

    /**
     * Binds a tooltip to leaflet; The layer element may already be added or not yet. If the layer element has not been
     * added yet the tooltip binding is deferred until the layer element was added.
     */
    public void bindTooltip(LTooltip tooltip) {
        if (tooltipBind != null)
            this.unbindTooltip();

        tooltipBind = () -> {
            this.getAttachedMap().getElement().executeJs("this.bindTooltip($0, $1)", this.getMapItemId(), tooltip.toJson());
        };
        if (this.isAttachedToMap())
            tooltipBind.run();
        else
            getOnAddQueue().add(tooltipBind);
    }

    public void unbindTooltip() {
        if (this.isAttachedToMap())
            this.getAttachedMap().getElement().executeJs("this.unbindTooltip($0)", this.getMapItemId());
        else
            getOnAddQueue().removeFirstOccurrence(tooltipBind);

    }

    /**
     * Binds a popup to leaflet; Leaflet handles that an old popup will be unbound automatically.
     */
    public void bindPopup(LPopup popup) {
        if (popupBind != null)
            unbindPopup();

        popupBind = () -> {
            this.getAttachedMap().getElement().executeJs("this.bindPopup($0, $1)", this.getMapItemId(), popup.toJson());
        };

        if (this.isAttachedToMap())
            popupBind.run();
        else
            getOnAddQueue().add(popupBind);

    }

    public void unbindPopup() {
        if (this.isAttachedToMap())
            this.getAttachedMap().getElement().executeJs("this.unbindPopup($0)", this.getMapItemId());
        else
            getOnAddQueue().removeFirstOccurrence(popupBind);

    }
}
