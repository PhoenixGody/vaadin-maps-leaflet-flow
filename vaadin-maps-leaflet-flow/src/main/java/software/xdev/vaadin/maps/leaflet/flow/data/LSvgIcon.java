package software.xdev.vaadin.maps.leaflet.flow.data;

import org.jetbrains.annotations.Nullable;

/**
 * A generic marker that is generated via a svg-tag.
 * There is an option to set some text (about 2 characters) and the colors of the stroke, filling and the text-color may be customized.
 */
public class LSvgIcon extends LDivIcon {

    private String iconPathFill;
    private String iconPathStroke;
    private String iconTextFill;

    private String text;

    /**
     * creates the most basic version of a svg marker icon with no text and default colors
     */
    public LSvgIcon() {
        this(null);
    }

    /**
     * creates a svg marker icon with custom text and default colors
     */
    public LSvgIcon(@Nullable String text) {
        this(text, null, null, null);
    }

    /**
     * creates the most customized version of a svg marker icon
     */
    public LSvgIcon(@Nullable String text, @Nullable String iconPathFill, @Nullable String iconPathStroke, @Nullable String iconTextFill) {
        super();
        this.iconPathFill = iconPathFill;
        this.iconPathStroke = iconPathStroke;
        this.iconTextFill = iconTextFill;
        this.text = text;

        updateHtml();

        setIconSize(24, 40);
        setIconAnchor(12, 40);//12 because half the size of the width (tip is in the middle)
        setClassName(getClassName() + " svg-icon");
    }

    /**
     * Generates the html content and places it as DivContent
     */
    protected void updateHtml() {
        this.setHtml(generateHtml());
    }

    /**
     * Generates html to  be placed as a marker-icon depending on the current configuration.
     */
    protected String generateHtml() {
        String pathFillColor = iconPathFill != null ? iconPathFill : "#44AEEA";
        String pathStrokeColor = iconPathStroke != null ? iconPathStroke : "#005FA8";
        String textFillColor = iconTextFill != null ? iconTextFill : "#fff";

        // the following code is basically copied and then modified to the new vaadin logic.
        // credits go to "Matti Tahvonen" ("@mstahv" on github)
        // source: https://github.com/mstahv/v-leaflet/blob/a0e8dce3ae40bff264ef3202f64bc661f1173db1/src/main/java/org/vaadin/addon/leaflet/client/LeafletMarkerConnector.java#L116
        StringBuilder svgSb = new StringBuilder();
        svgSb.append("<svg style=\"position:absolute\";width=\"100%\";height=\"100%\";>");
        svgSb.append("<path fill=\"");
        svgSb.append(pathFillColor);
        svgSb.append("\" stroke=\"");
        svgSb.append(pathStrokeColor);
        svgSb.append("\" d=\"M12.544,0.5C5.971,0.5,0.5,6.24,0.5,12.416c0,2.777,1.564,6.308,2.694,8.745\n"
                + "L12,38.922l9.262-17.761c1.13-2.438,2.738-5.791,2.738-8.745C24.5,6.24,19.117,0.5,12.544,0.5L12.544,0.5z\"/>");
        svgSb.append("<text fill=\"");
        svgSb.append(textFillColor);
        svgSb.append("\" x=\"12\" y=\"20\" text-anchor=\"middle\" font-size=\"16\" class=\"");
        svgSb.append("\">");
        if (this.text != null)
        {
            svgSb.append(this.text);
        }
        svgSb.append("</text></svg>");

        return  svgSb.toString();
    }

    /**
     * Sets the marker-icons filling color and updates the markers html.
     * This will only work until the Marker with the icon is added to the map.
     *
     * @param iconPathFill color in hex-form, e.g.: #ff0055 or a #fff shorthand, or with transparency: #33ff0055
     */
    public void setIconPathFill(@Nullable String iconPathFill) {
        if (this.iconPathFill != iconPathFill) {
            this.iconPathFill = iconPathFill;
            updateHtml();
        }
    }

    /**
     * Sets the marker-icons stroke color and updates the markers html.
     * This will only work until the Marker with the icon is added to the map.
     *
     * @param iconPathStroke color in hex-form, e.g.: #ff0055 or a #fff shorthand, or with transparency: #33ff0055
     */
    public void setIconPathStroke(@Nullable String iconPathStroke) {
        if (this.iconPathStroke != iconPathStroke) {
            this.iconPathStroke = iconPathStroke;
            updateHtml();
        }
    }

    /**
     * Sets the marker-icons  text color and updates the markers html.
     * This will only work until the Marker with the icon is added to the map.
     *
     * @param iconTextFill color in hex-form, e.g.: #ff0055 or a #fff shorthand, or with transparency: #33ff0055
     */
    public void setIconTextFill(@Nullable String iconTextFill) {
        if (this.iconTextFill != iconTextFill) {
            this.iconTextFill = iconTextFill;
            updateHtml();
        }
    }
}
