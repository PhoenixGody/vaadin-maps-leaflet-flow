
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.jetbrains.annotations.NotNull;


public class LMarker extends LLayer
{
	private static final String MARKER_TYPE = "Point";
	private LMarkerGeometry geometry;
	private LMarkerOptions properties;
	private LMarkerExtras extras;

	/**
	 * Creates a new Marker at the latitude, longitude
	 *
	 * @param lat
	 * @param lon
	 */
	public LMarker(final double lat, final double lon)
	{
		super();
		this.geometry = new LMarkerGeometry(MARKER_TYPE, lat, lon);
		this.properties = new LMarkerOptions();
		this.extras = new LMarkerExtras();
	}


	public LIcon getIcon()
	{
		return this.extras.getIcon();
	}

	public void setDivIcon(final LDivIcon icon)
	{
		this.extras.setIcon(icon);
	}

	public LIcon getDivIcon()
	{
		return this.extras.getIcon();
	}

	public void setIcon(final LIcon icon)
	{
		this.extras.setIcon(icon);
	}

	public LMarkerGeometry getGeometry()
	{
		return this.geometry;
	}

	public void setGeometry(final LMarkerGeometry geometry)
	{
		this.geometry = geometry;
	}

	public LMarkerOptions getProperties()
	{
		return this.properties;
	}

	public void setProperties(final LMarkerOptions properties)
	{
		this.properties = properties;
	}

	@NotNull
	public LMarkerExtras getExtras() {
		return extras;
	}

	public void setExtras(@NotNull LMarkerExtras extras) {
		this.extras = extras;
	}

	public double getLat()
	{
		return this.geometry.getCoordinates().get(0);
	}

	public void setLat(final double lat)
	{
		this.geometry.getCoordinates().remove(0);
		this.geometry.getCoordinates().set(0, lat);
	}

	public double getLon()
	{
		return this.geometry.getCoordinates().get(1);
	}

	public void setLon(final double lon)
	{
		this.geometry.getCoordinates().remove(1);
		this.geometry.getCoordinates().set(1, lon);
	}

	/**
	 * @deprecated Use LPopup instead
	 */
	@Deprecated
	public String getPopup()
	{
		return this.properties.getPopup();
	}

	/**
	 * Sets a Pop-up to the Marker
	 * @deprecated Use LPopup instead
	 */
	@Deprecated
	public void setPopup(final String popup)
	{
		this.properties.setPopup(popup);
	}

	@Override
	public JsonValue toJson()
	{
		final JsonObject jsonObject = Json.createObject();
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			jsonObject.put("geometry", Json.parse(mapper.writeValueAsString(this.geometry)));
		}
		catch(final JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
		jsonObject.put("properties", this.properties.toJson());
		jsonObject.put("extras", this.extras.toJson());

		return jsonObject;
	}

	@Override
	public String getJsFunctionForAddingToMap()
	{
		return "addMarker";
	}
}
