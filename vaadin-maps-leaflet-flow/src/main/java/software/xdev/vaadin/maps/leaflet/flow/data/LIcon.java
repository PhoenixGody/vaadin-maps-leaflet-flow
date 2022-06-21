
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import software.xdev.vaadin.maps.leaflet.flow.LMap;
import software.xdev.vaadin.maps.leaflet.flow.data.base.CanConvertToJson;


public class LIcon implements CanConvertToJson
{
	@JsonIgnore
	private String type;
	private String iconUrl;
	
	@JsonInclude(Include.NON_NULL)
	private List<Integer> iconSize;
	private final List<Integer> iconAnchor = new ArrayList<>();
	private final List<Integer> popupAnchor = new ArrayList<>();
	private final List<Integer> tooltipAnchor = new ArrayList<>();
	private String shadowUrl;
	private final List<Integer> shadowSize = new ArrayList<>();
	private final List<Integer> shadowAnchor = new ArrayList<>();
	
	public LIcon()
	{
		this.setIconAnchor(12, 41);
		this.setPopupAnchor(1, -34);
		this.setShadowSize(41, 41);
		this.setShadowAnchor(12, 41);
		this.iconUrl = "https://unpkg.com/leaflet@" + LMap.LEAFLET_VERSION + "/dist/images/marker-icon.png";
		this.shadowUrl = "https://unpkg.com/leaflet@" + LMap.LEAFLET_VERSION + "/dist/images/marker-shadow.png";
		this.type = "Icon";
		
	}
	
	public LIcon(final String url)
	{
		this.setIconAnchor(0, 0);
		this.setPopupAnchor(0, 0);
		this.setShadowSize(0, 0);
		this.setShadowAnchor(0, 0);
		this.iconUrl = url;
		this.type = "Icon";
		
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(final String type)
	{
		this.type = type;
	}
	
	public String getIconUrl()
	{
		return this.iconUrl;
	}
	
	/**
	 * Sets a icon url.
	 *
	 * @param iconUrl
	 */
	public void setIconUrl(final String iconUrl)
	{
		this.iconUrl = iconUrl;
	}
	
	public List<Integer> getIconSize()
	{
		return this.iconSize;
	}
	
	/**
	 * Icon size with x, y in px
	 *
	 * @param x
	 * @param y
	 */
	public void setIconSize(final int x, final int y)
	{
		if (this.iconSize == null)
			this.iconSize = new ArrayList<>();
		else
			this.iconSize.clear();

		this.iconSize.add(x);
		this.iconSize.add(y);
	}
	
	public List<Integer> getIconAnchor()
	{
		return this.iconAnchor;
	}
	
	/**
	 * Anchor point of the icon in x, y px.
	 *
	 * @param x
	 * @param y
	 */
	public void setIconAnchor(final int x, final int y)
	{
		this.iconAnchor.clear();
		this.iconAnchor.add(x);
		this.iconAnchor.add(y);
	}
	
	public List<Integer> getPopupAnchor()
	{
		return this.popupAnchor;
	}
	
	/**
	 * Anchor point of the Pop-up message in x,y px.
	 *
	 * @param x
	 * @param y
	 */
	public void setPopupAnchor(final int x, final int y)
	{
		this.popupAnchor.clear();
		this.popupAnchor.add(x);
		this.popupAnchor.add(y);
	}

	public List<Integer> getTooltipAnchor()
	{
		return this.tooltipAnchor;
	}

	/**
	 * Anchor point of the Pop-up message in x,y px.
	 *
	 * @param x
	 * @param y
	 */
	public void setTooltipAnchor(final int x, final int y)
	{
		this.tooltipAnchor.clear();
		this.tooltipAnchor.add(x);
		this.tooltipAnchor.add(y);
	}

	public String getShadowUrl()
	{
		return this.shadowUrl;
	}
	
	public void setShadowUrl(final String shadowUrl)
	{
		this.shadowUrl = shadowUrl;
	}
	
	public List<Integer> getShadowSize()
	{
		return this.shadowSize;
	}
	
	public void setShadowSize(final int x, final int y)
	{
		this.shadowSize.clear();
		this.shadowSize.add(x);
		this.shadowSize.add(y);
	}
	
	public List<Integer> getShadowAnchor()
	{
		return this.shadowAnchor;
	}
	
	public void setShadowAnchor(final int x, final int y)
	{
		this.shadowAnchor.clear();
		this.shadowAnchor.add(x);
		this.shadowAnchor.add(y);
	}

	@Override
	public JsonValue toJson() {
		final JsonObject jsonObject = Json.createObject();
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			//using jackson json just like bevor moving the code because at this stage it is simple
			jsonObject.put("properties", Json.parse(mapper.writeValueAsString(this)));
		}
		catch(final JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}
		jsonObject.put("type", this.getType());

		return jsonObject;
	}
}
