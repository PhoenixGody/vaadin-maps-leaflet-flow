
package software.xdev.vaadin.maps.leaflet.flow;

/*-
 * #%L
 * vaadin-maps-leaflet-flow
 * %%
 * Copyright (C) 2019 XDEV Software
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.*;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.LCenter;
import software.xdev.vaadin.maps.leaflet.flow.data.LLocateOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer;
import software.xdev.vaadin.maps.leaflet.flow.event.LocationErrorEvent;
import software.xdev.vaadin.maps.leaflet.flow.event.LocationEvent;
import software.xdev.vaadin.maps.leaflet.flow.event.MarkerClickEvent;
import software.xdev.vaadin.maps.leaflet.flow.event.MoveEndEvent;



@NpmPackage(value = "leaflet", version = "^" + LMap.LEAFLET_VERSION)
@JsModule("./leaflet/leafletCon.js")
@Tag("leaflet-map")
public class LMap extends Component implements HasSize, HasStyle
{
	// the common used version of leaflet
	public static final String LEAFLET_VERSION = "1.8.0";


	private static final String GET_ITEM_FUNCTION = "getItem";
	public static final String GET_ITEM_FUNCTION_CALL = "this." + LMap.GET_ITEM_FUNCTION + "($0)";
	private static final String SET_VIEW_POINT_FUNCTION = "setViewPoint";

	private static final String TILE_LAYER_FUNCTION = "setTileLayer";
	private static final String SET_ZOOM_FUNCTION = "setZoomLevel";

	private static final String START_LOCATE_FUNCTION = "startLocate";
	private static final String STOP_LOCATE_FUNCTION = "stopLocate";

	private static final String ZOOM_TO_CONTENT_FUNCTION = "zoomToContent";
	private static final String RECALCULATE_AUTO_COLLIDING_TOOLTIP_VISIBILITY = "recalculateAutoCollidingTooltipVisibility";

	private LCenter center;
	private final Map<String, LManagedComponent> components = new TreeMap<>();

	/**
	 * @deprecated Use {@link LMap#LMap(double, double, int, software.xdev.vaadin.maps.leaflet.flow.data.LTileLayer)}
	 * 	because this will not automagically add a OpenStreetMap tile layer
	 */
	@Deprecated
	public LMap(final double lat, final double lon, final int zoom)
	{
		this(lat, lon, zoom, LTileLayer.osmTileLayer());
	}

	/**
	 *
	 * @param lat latitude start position for the center
	 * @param lon longitude start position for the center
	 * @param zoom start zoom level
	 * @param tileLayer tile layer that is added directly to the map. If null is given no layer is added
	 */
	public LMap(final double lat, final double lon, final int zoom, @Nullable LTileLayer tileLayer)
	{
		this.center = new LCenter(lat, lon, zoom);
		this.setViewPoint(this.center);
		this.setFixZIndexEnabled(true);

		if (tileLayer != null)
			setTileLayer(tileLayer);
	}
	
	/**
	 * @deprecated Just used for demo purposes
	 */
	@Deprecated
	public LMap()
	{
		this(50.921273, 10.359164, 6);
	}
	
	public void setZoom(final int zoom)
	{
		this.getElement().callJsFunction(SET_ZOOM_FUNCTION, zoom);
	}
	
	public void setViewPoint(final LCenter viewpoint)
	{
		this.getElement().callJsFunction(SET_VIEW_POINT_FUNCTION, viewpoint.toJson());
	}
	
	public void setTileLayer(final LTileLayer tileLayer)
	{
		if (!getComponents().containsKey(tileLayer.getMapItemId()))
			addLComponents(tileLayer);
		this.getElement().callJsFunction(TILE_LAYER_FUNCTION, tileLayer.getMapItemId());
	}
	
	/**
	 * This fixes situations where the leafletmap overlays components like Dialogs
	 * 
	 * @param enabled
	 *            enable or disable the fix
	 */
	protected void setFixZIndexEnabled(final boolean enabled)
	{
		this.getStyle().set("z-index", enabled ? "1" : null);
	}
	
	/**
	 * add Leaflet component(s) to the map
	 *
	 * @param lObjects
	 * 
	 * @deprecated Use {@link LMap#addLComponents(LManagedComponent...)} instead
	 */
	@Deprecated
	public void addLComponent(final LManagedComponent... lObjects)
	{
		this.addLComponents(lObjects);
	}
	
	/**
	 * add Leaflet component(s) to the map
	 *
	 * @param lComponents
	 */
	public void addLComponents(final LManagedComponent... lComponents)
	{
		this.addLComponents(Arrays.asList(lComponents));
	}
	
	/**
	 * add Leaflet components to the map
	 *
	 * @param lComponents
	 */
	public void addLComponents(final Collection<LManagedComponent> lComponents)
	{
		for(final LManagedComponent lComponent : lComponents)
		{
			this.addLComponent(lComponent);
		}
	}
	
	protected void addLComponent(final LManagedComponent lComponent)
	{
		if (lComponent.getJsFunctionForAddingToMap() == null)
			throw new IllegalArgumentException("invalid lcomponent");

		if (lComponent.isAttachedToMap())
			throw new IllegalStateException("The given lcomponent has already been added in a LMap");

		lComponent.setAttachedMap(this);
		this.getComponents().put(lComponent.getMapItemId(), lComponent);
		this.getElement().callJsFunction(lComponent.getJsFunctionForAddingToMap(), lComponent.getMapItemId(), lComponent.toJson());

		while (lComponent.getOnAddQueue().size() > 0)
			lComponent.getOnAddQueue().removeFirst().run();
	}
	
	/**
	 * Removes a map item
	 *
	 * @param items
	 * 
	 * @deprecated Use {@link LMap#removeLComponents(LManagedComponent...)}
	 */
	@Deprecated
	public void removeItem(final LManagedComponent... items)
	{
		this.removeLComponents(items);
	}

	/**
	 * remove Leaflet component(s) to the map
	 *
	 * @param lComponents
	 */
	public void removeLComponents(final LManagedComponent... lComponents)
	{
		this.removeLComponents(Arrays.asList(lComponents));
	}
	
	/**
	 * remove Leaflet components to the map
	 *
	 * @param lComponents
	 */
	public void removeLComponents(final Collection<LManagedComponent> lComponents)
	{
		for(final LManagedComponent lComponent : lComponents)
		{
			this.removeLComponent(lComponent);
		}
	}
	
	protected void removeLComponent(final LManagedComponent lComponent)
	{
		getComponents().remove(lComponent.getMapItemId());
		lComponent.setAttachedMap(null);
		this.getElement().callJsFunction(lComponent.getJsFunctionForRemovingFromMap(), lComponent.getMapItemId());
	}

	/**
	 *
	 * @return
	 *
	 * @deprecated Use {@link LMap#getComponents()}
	 */
	@Deprecated
	public Map<String, LManagedComponent> getItems()
	{
		return this.components;
	}

	/**
	 * Returns a new component list
	 * @return
	 */
	public Map<String, LManagedComponent> getComponents()
	{
		return this.components;
	}
	
	public LCenter getCenter()
	{
		return this.center;
	}
	
	/**
	 * Starting Point of the map with latitude, longitude and zoom level
	 *
	 * @param start
	 */
	public void setCenter(final LCenter start)
	{
		this.center = start;
		this.setViewPoint(start);
	}

	public Registration addMarkerClickListener(final ComponentEventListener<MarkerClickEvent> listener)
	{
		return ComponentUtil.addListener(this, MarkerClickEvent.class, listener);
	}

	public Registration addMoveEndListener(final ComponentEventListener<MoveEndEvent> listener)
	{
		return ComponentUtil.addListener(this, MoveEndEvent.class, listener);
	}

	public void startLocate(@NotNull LLocateOptions locateOptions) {
		this.getElement().callJsFunction(START_LOCATE_FUNCTION, locateOptions.toJson());
	}

	public void stopLocate() {
		this.getElement().callJsFunction(STOP_LOCATE_FUNCTION);
	}

	public Registration addOnLocateSuccessListener(final ComponentEventListener<LocationEvent> listener)
	{
		return ComponentUtil.addListener(this, LocationEvent.class, listener);
	}


	public Registration addOnLocateFailListener(final ComponentEventListener<LocationErrorEvent> listener)
	{
		return ComponentUtil.addListener(this, LocationErrorEvent.class, listener);
	}

	public void zoomToContent() {
		this.getElement().callJsFunction(ZOOM_TO_CONTENT_FUNCTION);
	}

	/**
	 * Performs the recalculation of the visibility of all permanent, autocolliding tooltips.
	 * This can be triggered by the flow api for better performance after elements have been added.
	 */
	public void recalculateAutoCollidingTooltipVisibility() {
		this.getElement().callJsFunction(RECALCULATE_AUTO_COLLIDING_TOOLTIP_VISIBILITY);
	}
}
