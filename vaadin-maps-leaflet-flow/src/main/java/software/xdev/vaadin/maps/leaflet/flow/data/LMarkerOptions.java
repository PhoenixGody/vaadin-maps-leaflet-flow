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

import org.jetbrains.annotations.Nullable;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasInteractiveOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.HasMarkerOptions;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsBase;
import software.xdev.vaadin.maps.leaflet.flow.data.options.LeafletOptionsContainerKey;

public class LMarkerOptions extends LeafletOptionsBase implements HasInteractiveOptions, HasMarkerOptions
{
	/**
	 * @deprecated Use LPopup instead
	 */
	@Deprecated
	private String popup;


	/**
	 * @deprecated Use LPopup instead
	 */
	@Deprecated
	public String getPopup()
	{
		return this.popup;
	}

	/**
	 * @deprecated Use bindPopup instead
	 */
	@Deprecated
	public void setPopup(final String popup)
	{
		this.popup = popup;
	}
}
