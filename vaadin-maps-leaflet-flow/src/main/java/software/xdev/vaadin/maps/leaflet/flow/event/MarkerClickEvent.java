package software.xdev.vaadin.maps.leaflet.flow.event;

/*-
 * #%L
 * LeafletMap for Vaadin
 * %%
 * Copyright (C) 2019 - 2022 XDEV Software
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

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.base.HasMapItemId;
import software.xdev.vaadin.maps.leaflet.flow.data.LLatLng;
import software.xdev.vaadin.maps.leaflet.flow.data.base.LComponent;
import software.xdev.vaadin.maps.leaflet.flow.data.convert.JsonLeafletConverter;

@DomEvent("map-leaflet-marker-clicked")
public class MarkerClickEvent extends ComponentEvent<LMap> implements HasMapItemId {
    private String mapItemId;
    private LComponent affectedMapItem;
    private LLatLng position;

    public MarkerClickEvent(final LMap source, final boolean fromClient,
                            @EventData("event.detail.mapItemId") String pMapItemId,
                            @EventData("event.detail.position") JsonValue pPosition) {
        super(source, fromClient);
        mapItemId = pMapItemId;
        affectedMapItem = source.getComponents().get(getMapItemId());
        position = JsonLeafletConverter.jsonValue2LLatLng(pPosition);
    }

    public String getMapItemId() {
        return mapItemId;
    }

    public LComponent getAffectedMapItem() {
        return affectedMapItem;
    }

    public LLatLng getPosition() {
        return position;
    }
}
