
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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;


/**
 * Leaflet polygon that uses the {@link LPolygonGeometry} as information source for coordinates.
 */
public class LPolygon extends LPolygonBase {
	private final LPolygonGeometry geometry;

	public LPolygon(final LPoint... points)
	{
		super();
		final List<List<Double>> posis = new ArrayList<>();
		for(final LPoint p : points)
		{
			posis.add(p.getCoords());
		}
		this.geometry = new LPolygonGeometry("Polygon", posis);
	}
	
	/**
	 * Create a new Polygon for marking a area on the map
	 *
	 * @param points
	 *            List of points to draw the Polygon
	 */
	public LPolygon(final List<LPoint> points)
	{
		super();
		final List<List<Double>> posis = new ArrayList<>();
		for(final LPoint p : points)
		{
			posis.add(p.getCoords());
		}

		this.geometry = new LPolygonGeometry("Polygon", posis);
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject result = (JsonObject) super.toJson();
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			result.put("geometry", Json.parse(mapper.writeValueAsString(this.geometry)));
		}
		catch(final JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
		
		return result;
	}
}
