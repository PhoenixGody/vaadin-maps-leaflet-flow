package software.xdev.vaadin.maps.leaflet.flow.data;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Marker Icons based on vaadin icons. This uses the {@link LSvgIcon} and adds the icon-element on top of it.
 */
public class LVaadinIconBasedIcon extends LSvgIcon {

    private String vaadinIconHtml;


    public LVaadinIconBasedIcon(@NotNull Icon icon) {
        this(icon, null, null, null);
    }

    public LVaadinIconBasedIcon(@NotNull IconFactory iconFactory) {
        this(iconFactory, null, null, null);
    }

    public LVaadinIconBasedIcon(@NotNull IconFactory iconFactory, @Nullable String iconPathFill, @Nullable String iconPathStroke, @Nullable String vaadinIconFill) {
        this(createIconFromFactory(iconFactory, vaadinIconFill), iconPathFill, iconPathStroke, vaadinIconFill);

    }

    public LVaadinIconBasedIcon(@NotNull Icon icon, @Nullable String iconPathFill, @Nullable String iconPathStroke, @Nullable String vaadinIconFill) {
        super(null, iconPathFill, iconPathStroke, null);
        if (vaadinIconFill != null)
            icon.setColor(vaadinIconFill);
        vaadinIconHtml = icon.getElement().getOuterHTML();

        String htmlContent = this.getHtml();
        this.setHtml(htmlContent + vaadinIconHtml);
    }

    @Override
    protected String generateHtml() {
        String result = super.generateHtml();
        if (vaadinIconHtml != null)
            result += vaadinIconHtml;
        return result;
    }

    private static Icon createIconFromFactory(@NotNull IconFactory iconFactory, @Nullable String vaadinIconFill) {
        Icon icon = iconFactory.create();
        icon.getElement().getStyle().set("position", "relative");
        icon.getElement().getStyle().set("z-index", "210");
        icon.getElement().getStyle().set("margin-left", "16%");
        icon.getElement().getStyle().set("margin-right", "16%");
        icon.getElement().getStyle().set("margin-top", "4px");
        icon.getElement().getStyle().set("width", "68%");
        if (vaadinIconFill != null)
            icon.setColor(vaadinIconFill);
        return icon;
    }
}
