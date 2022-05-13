
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
import elemental.json.JsonArray;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class LPoint implements CanConvertToJson
{
	private List<Double> coords = new ArrayList<>();
	
	public LPoint(final double lat, final double lon)
	{
		this.coords.add(lat);
		this.coords.add(lon);
	}
	
	public List<Double> getCoords()
	{
		return this.coords;
	}
	
	public void setCoords(final List<Double> point)
	{
		this.coords = point;
	}

	@Override
	@Nullable
	public JsonValue toJson() {
		if (getCoords().size() != 2)
			return null;

		JsonArray result = Json.createArray();
		result.set(0, getCoords().get(0));
		result.set(1, getCoords().get(1));
		return result;
	}
}
