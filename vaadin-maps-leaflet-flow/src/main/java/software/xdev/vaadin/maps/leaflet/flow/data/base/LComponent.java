package software.xdev.vaadin.maps.leaflet.flow.data.base;

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


/**
 * 
 * Marker Interface for Leaflet Components
 *
 */
public interface LComponent extends CanConvertToJson, HasMapItemId {
	static final String DELETE_FUNCTION = "deleteItem";

	/**
	 * Get the JavaScript Function for adding the component to a map
	 * @return
	 */
	String getJsFunctionForAddingToMap();


	default String getJsFunctionForRemovingFromMap()
	{
		return DELETE_FUNCTION;
	}
}
