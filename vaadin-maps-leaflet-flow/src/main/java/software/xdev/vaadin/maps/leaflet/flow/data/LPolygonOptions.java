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

import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;

/**
 * In Leaflet the polygon options inherit the polyline options, so let's do this here the same way.
 */
public class LPolygonOptions extends LPolylineOptions
{
	public static final LPolygonOptions createDefault()
	{
		LPolygonOptions result = new LPolygonOptions();
		// use the same values as before the refactoring:
		// path options
		result.setStroke(true);
		result.setColor("#000000");
		result.setOpacity(1.0);
		result.setWeight(3);
		result.setLineJoin("round");
		result.setFill(false);
		result.setFillColor("#ffffff");
		result.setFillOpacity(0.2);
		result.setFillRule("nonzero");
		// polyline options
		result.setNoClip(true);
		result.setSmoothFactor(0.5);
		return result;
	}

	// todo: remove the deprecated popup code
	private String popup;
	public String getPopup()
	{
		return this.popup;
	}
		public void setPopup(final String popup)
	{
		this.popup = popup;
	}
	
}
