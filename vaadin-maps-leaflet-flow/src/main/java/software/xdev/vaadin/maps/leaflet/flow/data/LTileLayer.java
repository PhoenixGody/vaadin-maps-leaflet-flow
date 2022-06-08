package software.xdev.vaadin.maps.leaflet.flow.data;

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

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;

import java.util.UUID;


public class LTileLayer extends LManagedComponent
{
	private LTileLayerOptions options;

	private String link;
	private String id;

	public LTileLayer() {
		super();
		options = new LTileLayerOptions();
	}

	public LTileLayer(final String link, final String attribution, final Integer maxZoom, final String id)
	{
		this();
		setAttribution(attribution);
		getOptions().setMaxZoom(maxZoom);
		this.link = link;
		this.id = id;
	}
	
	public String getLink()
	{
		return this.link;
	}
	
	public void setLink(final String link)
	{
		this.link = link;
	}
	
	public String getAttribution()
	{
		return this.options.getAttribution();
	}
	
	public void setAttribution(final String attribution)
	{
		this.options.setAttribution(attribution);
	}

	/**
	 * @deprecated use {@link #getOptions()} and {@link LTileLayerOptions#getMaxZoom()} instead
	 */
	@Deprecated
	public int getZoom()
	{
		return this.options.getMaxZoom() == null ? 18 : this.options.getMaxZoom();
	}

	/**
	 * @deprecated use {@link #getOptions()} and {@link LTileLayerOptions#setMaxZoom(Integer)} instead
	 */
	@Deprecated
	public void setZoom(final int zoom)
	{
		this.options.setMaxZoom(zoom);
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(final String id)
	{
		this.id = id;
	}


	@NotNull
	public LTileLayerOptions getOptions() {
		return options;
	}

	public void setOptions(@NotNull LTileLayerOptions options) {
		this.options = options;
	}

	@Override
	public JsonValue toJson()
	{
		final JsonObject result = Json.createObject();

		final JsonObject tileLayer = Json.createObject();
		tileLayer.put("link", getLink());
		result.put("tile", tileLayer);

		JsonObject optionsJson = (JsonObject) options.toJson();
		if (getId() != null)
			optionsJson.put("id", getId());//todo: check if necessary; id is not part of the leaflet TileLayer options
		result.put("properties", optionsJson);

		return result;
	}

	/**
	 * Generates an openStreetMap Tile Layer.
	 * Use this with caution and please respect the official usage policies.
	 */
	public static LTileLayer osmTileLayer()
	{
		return new LTileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
				"© <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a>", 18,
				UUID.randomUUID().toString());
	}

	@Override
	public String getJsFunctionForAddingToMap() {
		return "addTileLayer";
	}

	@Override
	public String getJsFunctionForRemovingFromMap() {
		return "removeTileLayer";
	}
}
