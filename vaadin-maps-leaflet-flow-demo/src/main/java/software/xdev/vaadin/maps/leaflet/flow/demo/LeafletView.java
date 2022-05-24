package software.xdev.vaadin.maps.leaflet.flow.demo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.*;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.control.LControlLayers;
import software.xdev.vaadin.maps.leaflet.flow.data.control.LControlLayersBaseConfig;
import software.xdev.vaadin.maps.leaflet.flow.data.control.LControlLayersOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.popup.LPopup;
import software.xdev.vaadin.maps.leaflet.flow.data.popup.LPopupOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.tooltip.LTooltip;
import software.xdev.vaadin.maps.leaflet.flow.data.tooltip.LTooltipOptions;


@Route("")
public class LeafletView extends VerticalLayout
{
	private boolean viewLunch = false;
	
	/**
	 * UI-Components
	 */
	private final Button btnLunch = new Button("Where do XDEV employees go for lunch?");
	private final Button btnOpenDialog = new Button("Open dialog over map", ev ->
	{
		final Icon icoClose = VaadinIcon.CLOSE.create();
		
		final Dialog dialog = new Dialog(icoClose);
		dialog.setWidth("50vw");
		dialog.setHeight("50vh");
		dialog.open();
		
		icoClose.addClickListener(iev -> dialog.close());
	});

	private final Button btnSetLayer = new Button("Set Layer to OMS (German)", this::setOSMGermanyLayer);
	private final Button btnRemoveLayer = new Button("Remove Layer OMS (German)", this::removeOSMGermanyLayer);

	private final Button btnStartLocation = new Button("Start locating", this::startLocation);
	private final Button btnStopLocation = new Button("Stop locating", this::stopLocation);
	private final Button btnZoomToContent = new Button("Zoom to content", this::doZoomToContent);


	private LMap map;
	
	private LMarker markerZob;
	private LMarker markerRathaus;
	
	private LCircle circleRange;
	private LMarker markerPizza;
	private LMarker markerKebab;
	private LMarker markerAsia;
	private LMarker markerGreek;
	private LMarker markerBakery;
	private LMarker markerLeberkaese;
	private LTileLayer osmLayer;
	private LTileLayer osmLayerDE;
	private LTooltip markerRathausTooltip;

	public LeafletView()
	{
		this.initMapComponents();
		
		this.btnLunch.addClickListener(this::btnLunchClick);
		this.add(this.btnLunch, this.btnOpenDialog, this.btnZoomToContent);
		this.add(new HorizontalLayout(this.btnSetLayer, this.btnRemoveLayer));
		this.add(new HorizontalLayout(this.btnStartLocation, this.btnStopLocation));
		this.add(new Button("open Townhall Tooltip", e -> this.markerRathaus.toggleTooltip()));
	}
	
	private void btnLunchClick(final ClickEvent<Button> event)
	{
		this.viewLunch = !this.viewLunch;
		
		final List<LManagedComponent> normalComponents = Arrays.asList(this.markerRathaus, this.markerZob);
		final List<LManagedComponent> lunchComponents = Arrays.asList(
			this.circleRange,
			this.markerPizza,
			this.markerKebab,
			this.markerAsia,
			this.markerGreek,
			this.markerBakery,
			this.markerLeberkaese);
		
		this.map.setViewPoint(new LCenter(49.675126, 12.160733, this.viewLunch ? 16 : 17));
		this.map.removeLComponents(this.viewLunch ? normalComponents : lunchComponents);
		this.map.addLComponents(this.viewLunch ? lunchComponents : normalComponents);
		
		this.btnLunch.setText(this.viewLunch ? "Go back to the normal view" : "Where do XDEV employees go for lunch?");
	}
	
	private void initMapComponents()
	{
		this.markerZob = new LMarker(49.673470, 12.160108);
		this.markerZob.setPopup("Central bus station");
		this.markerZob.setDivIcon(new LVaadinIconBasedIcon(VaadinIcon.BUS, "#E89C00", "#55266235", "#266235"));

		final LMarker markerXDev = new LMarker(49.675806677512824, 12.160990185846394);
		final LIcon xDevLogo = new LIcon(
			"https://www.xing.com/img/custom/communities/communities_files/f/f/6/32758/large/XDEV_600x600_red.png?1438789458");

		// other option:
		// xDevLogo.setIconUrl(
		// "https://www.xing.com/img/custom/communities/communities_files/f/f/6/32758/large/XDEV_600x600_red.png?1438789458");
		xDevLogo.setIconSize(70, 70);
		xDevLogo.setIconAnchor(33, 55);
		markerXDev.setPopup("<a href='https://www.xdev-software.de/'>Xdev-Software GmbH</a>");
		markerXDev.setIcon(xDevLogo);
		
		final LMarker markerInfo = new LMarker(49.674095, 12.162257);
		final LDivIcon div = new LDivIcon(
			"<p><center><b>Welcome to Weiden in der Oberpfalz!</b></center></p><p>This Demo shows you different Markers,<br> Popups, Polygon and other Stuff</p>");
		div.setIconSize(280, 100);
		
		// other options:
		// div.setIconSize(265, 90);
		// div.setHtml(
		// "<p><center><b>Welcome to Weiden in der Oberpfalz!</b></center></p><p>This Demo shows you different Markers,
		// Popups, Polygon and other Stuff</p>");
		//markerInfo.setDivIcon(div);
		
		final LPolygon polygonNoc = new LPolygon(
			Arrays.asList(
				new LPoint(49.674910, 12.159202),
				new LPoint(49.675719, 12.160248),
				new LPoint(49.675962, 12.160033),
				new LPoint(49.675691, 12.158011),
				new LPoint(49.675306, 12.158499)));
		polygonNoc.setFill(true);
		polygonNoc.setFillColor("#3366ff");
		polygonNoc.setFillOpacity(0.8);
		polygonNoc.setStroke(false);
		polygonNoc.setPopup("NOC-Nordoberpfalz Center");

		this.markerRathaus = new LMarker(49.675519, 12.163868);
		this.markerRathaus.setPopup("Old Town Hall");

		this.markerRathaus.setDivIcon(new LSvgIcon("01"));

		this.circleRange = new LCircle(49.675126, 12.160733, 450);
		
		this.markerPizza = new LMarker(49.674413, 12.160925);
		this.markerPizza.setPopup("Pizza!");
		
		this.markerKebab = new LMarker(49.673026, 12.156278);
		this.markerKebab.setPopup("Kebab!");
		
		this.markerAsia = new LMarker(49.675039, 12.162127);
		this.markerAsia.setPopup("Asian Food");
		
		this.markerGreek = new LMarker(49.675126, 12.161899);
		this.markerGreek.setPopup("Greek Food");
		
		this.markerBakery = new LMarker(49.674806, 12.160249);
		this.markerBakery.setPopup("Fresh baked stuff");
		
		this.markerLeberkaese = new LMarker(49.673800, 12.160113);
		this.markerLeberkaese.setPopup("Fast food like Leberkäsesemmeln");


		osmLayer = LTileLayer.osmTileLayer();
		osmLayerDE = new LTileLayer("https://{s}.tile.openstreetmap.de/{z}/{x}/{y}.png",
				"© <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a>", 18,
				UUID.randomUUID().toString());
		this.map = new LMap(49.675126, 12.160733, 17, this.osmLayer);

		this.map.addOnLocateSuccessListener((event) -> {
			Notification.show("Located successfully!");
		});
		this.map.addOnLocateFailListener((event) -> {
			Notification.show("Could not locate you!");
			this.map.stopLocate();
		});



		this.map.addLComponents(osmLayerDE);

		LControlLayersBaseConfig baseConfig = new LControlLayersBaseConfig();
		baseConfig.addTileLayer("OSM", osmLayer);
		baseConfig.addTileLayer("OSM (German)", osmLayerDE);
		LControlLayers lControlLayers = new LControlLayers(new LControlLayersOptions(null, null, true, null), baseConfig);
		this.map.addLComponents(lControlLayers);
		this.map.setTileLayer(osmLayer);


		this.map.setHeight("700px");
		this.map.setWidth("1000px");
		// it is necessary to inform the map the size has changed after modifying the size via the flow api because
		// otherwise the map would not be displayed correctly.
		this.map.getElement().executeJs("setTimeout(function (mapElement) {mapElement.invalidateSize(true);}, 1, this.map);");
		this.map.addMarkerClickListener(ev -> {
			System.out.println(ev.getMapItemId());
		}); // add some logic here for called Markers (token)

		map.addMoveEndListener(event -> System.out.println("moved!:"  + event.getBoundaries()));

		this.map.addLComponents(
			markerXDev,
			markerInfo,
			this.markerZob,
			polygonNoc,
			this.markerRathaus);

		this.markerRathaus.bindTooltip(new LTooltip("Old Townhall", this.markerRathaus, new LTooltipOptions(null, null, true, null, null)));
		LPopupOptions rathausPopupOptions = new LPopupOptions();
		rathausPopupOptions.setCloseButton(false);
		this.markerRathaus.bindPopup(new LPopup("Unterer Markt 2-6, 92637 Weiden in der Oberpfalz", this.markerRathaus, rathausPopupOptions));

		this.add(this.map);
	}

	private void setOSMGermanyLayer(ClickEvent<Button> event) {
		this.map.setTileLayer(osmLayerDE);
	}

	private void removeOSMGermanyLayer(ClickEvent<Button> event) {
		this.map.removeLComponents(osmLayerDE);
	}


	private void startLocation(ClickEvent<Button> buttonClickEvent) {
		this.map.startLocate(new LLocateOptions(true, false, null, null, null, true));
	}

	private void stopLocation(ClickEvent<Button> buttonClickEvent) {
		this.map.stopLocate();
	}

	private void doZoomToContent(ClickEvent<Button> buttonClickEvent) {
		this.map.zoomToContent();
	}
}
