
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
import software.xdev.vaadin.maps.leaflet.flow.LManagedComponent;


public class LCircle extends LLayer
{
	private LPoint geometry;
	private LCircleOptions properties;
	
	/**
	 * Creates a new circle
	 * @param lat Latitude
	 * @param lon Longitude
	 * @param radius radius
	 */
	public LCircle(final double lat, final double lon, final double radius)
	{
		super();
		this.geometry = new LPoint(lat, lon);
		this.properties = new LCircleOptions();
		this.properties.setRadius(radius);
	}
	
	public LPoint getGeometry()
	{
		return this.geometry;
	}
	
	public void setGeometry(final LPoint geometry)
	{
		this.geometry = geometry;
	}
	
	public LCircleOptions getProperties()
	{
		return this.properties;
	}
	
	public void setProperties(final LCircleOptions properties)
	{
		this.properties = properties;
	}
	
	public Boolean isStroke()
	{
		return this.properties.getStroke();
	}
	
	/**
	 * Draws a border, default is true.
	 *
	 * @param stroke stroke
	 */
	public void setStroke(final boolean stroke)
	{
		this.properties.setStroke(stroke);
	}
	
	public String getStrokeColor()
	{
		return this.properties.getColor();
	}
	
	/**
	 * Set a Color to the border.
	 *
	 * @param strokeColor color of the stroke
	 */
	public void setStrokeColor(final String strokeColor)
	{
		this.properties.setColor(strokeColor);
	}
	
	public Double getStrokeOpacity()
	{
		return this.properties.getOpacity();
	}
	
	/**
	 * Sets the opacity of the border.
	 *
	 * @param strokeOpacity opacity of the stroke
	 */
	public void setStrokeOpacity(final double strokeOpacity)
	{
		this.properties.setOpacity(strokeOpacity);
	}
	
	public Integer getStrokeWeight()
	{
		return this.properties.getWeight();
	}
	
	/**
	 * Sets the width of the border.
	 *
	 * @param strokeWeight strokeWeight
	 */
	public void setStrokeWeight(final int strokeWeight)
	{
		this.properties.setWeight(strokeWeight);
	}
	
	public String getLineJoin()
	{
		return this.properties.getLineJoin();
	}
	
	/**
	 * A string that defines shape to be used at the corners of the stroke.<br>
	 * <ul>
	 * 	<li>miter</li>
	 * 	<li>round</li>
	 * 	<li>bevel</li>
	 * 	<li>miter-clip</li>
	 * 	<li>arcs</li>
	 * </ul>
	 *
	 * @param lineJoin string that defines shape to be used at the corners of the stroke
	 */
	public void setLineJoin(final String lineJoin)
	{
		this.properties.setLineJoin(lineJoin);
	}
	
	public String getDashArray()
	{
		return this.properties.getDashArray();
	}
	
	/**
	 * A string that defines the stroke dash pattern.<br>
	 * For example: "2 1 3 1 2"
	 *
	 * @param dashArray string that defines the stroke dash pattern
	 */
	public void setDashArray(final String dashArray)
	{
		this.properties.setDashArray(dashArray);
	}
	
	public String getDashOffset()
	{
		return this.properties.getDashOffset();
	}
	
	/**
	 * A string that defines the distance into the dash pattern to start the dash.<br>
	 * For example: "2" - The start of the dash array computation is pulled by 3 user units
	 *
	 * @param dashOffset  string that defines the distance into the dash pattern to start the dash
	 */
	public void setDashOffset(final String dashOffset)
	{
		this.properties.setDashOffset(dashOffset);
	}
	
	public Boolean isFill()
	{
		return this.properties.getFill();
	}
	
	/**
	 * Whether to fill the path with color.<br>
	 * Set it to false to disable filling.
	 *
	 * @param fill false to disable filling
	 */
	public void setFill(final boolean fill)
	{
		this.properties.setFill(fill);
	}
	
	public String getFillColor()
	{
		return this.properties.getFillColor();
	}
	
	/**
	 * Fill color.
	 *
	 * @param fillColor fillColor
	 */
	public void setFillColor(final String fillColor)
	{
		this.properties.setFillColor(fillColor);
	}
	
	public Double getFillOpacity()
	{
		return this.properties.getFillOpacity();
	}
	
	/**
	 * Fill opacity.
	 *
	 * @param fillOpacity fillOpacity
	 */
	public void setFillOpacity(final double fillOpacity)
	{
		this.properties.setFillOpacity(fillOpacity);
	}

	public String getFillRule()
	{
		return this.properties.getFillRule();
	}
	
	/**
	 * A string that defines how the inside of a shape is determined.<br>
	 * <ul>
	 * 	<li>evenodd</li>
	 * 	<li>nonzero</li>
	 * </ul>
	 *
	 * @param fillRule string that defines how the inside of a shape is determined
	 */
	public void setFillRule(final String fillRule)
	{
		this.properties.setFillRule(fillRule);
	}
	
	public boolean isNoClip()
	{
		return this.properties.getNoClip();
	}
	
	/**
	 * Disable polyline clipping.
	 *
	 * @param noClip true = disable polyline clipping
	 */
	public void setNoClip(final boolean noClip)
	{
		this.properties.setNoClip(noClip);
	}
	
	public double getSmoothFactor()
	{
		return this.properties.getSmoothFactor();
	}
	
	/**
	 * How much to simplify the polyline on each zoom level.<br>
	 * More means better performance and smoother look, and less means more accurate representation.
	 *
	 * @param smoothFactor how much to simplify the polyline on each zoom level
	 */
	public void setSmoothFactor(final double smoothFactor)
	{
		this.properties.setSmoothFactor(smoothFactor);
	}
	
	public void setRadius(final double radius)
	{
		this.properties.setRadius(radius);
	}
	
	public Double getRadius()
	{
		return this.properties.getRadius();
	}
	
	@Override
	public JsonValue toJson()
	{
		final JsonObject jsonObject = Json.createObject();
		final JsonObject coords = Json.createObject();
		coords.put("coords", this.geometry.toJson());
		jsonObject.put("type", Json.create("Feature"));
		jsonObject.put("geometry", coords);
		jsonObject.put("properties", this.properties.toJson());
		return jsonObject;
	}

	@Override
	public String getJsFunctionForAddingToMap()
	{
		return "addCircle";
	}
	
}
