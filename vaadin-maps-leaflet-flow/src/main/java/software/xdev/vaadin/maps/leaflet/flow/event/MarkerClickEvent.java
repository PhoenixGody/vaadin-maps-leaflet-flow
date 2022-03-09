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
import software.xdev.vaadin.maps.leaflet.flow.LMap;

public class MarkerClickEvent extends ComponentEvent<LMap> {
    private final String tag;

    public MarkerClickEvent(final LMap source, final boolean fromClient, final String tag) {
        super(source, fromClient);
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }
}
