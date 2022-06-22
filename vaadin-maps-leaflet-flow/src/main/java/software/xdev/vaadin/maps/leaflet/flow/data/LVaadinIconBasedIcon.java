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

        super(null, iconPathFill, iconPathStroke, null, icon.getElement().getOuterHTML());

        if (vaadinIconFill != null)
            icon.setColor(vaadinIconFill);

    }


    private static Icon createIconFromFactory(@NotNull IconFactory iconFactory, @Nullable String vaadinIconFill) {
        Icon icon = iconFactory.create();
        icon.getElement().getStyle().set("margin-top", "5px");
        icon.getElement().getStyle().set("margin-left", "auto");
        icon.getElement().getStyle().set("margin-right", "auto");
        icon.getElement().getStyle().set("display", "block");
        if (vaadinIconFill != null)
            icon.setColor(vaadinIconFill);
        return icon;
    }
}
