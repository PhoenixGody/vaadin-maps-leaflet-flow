import {html,PolymerElement} from "@polymer/polymer/polymer-element.js";
//import "leaflet/dist/leaflet.js"; // for distribution
import * as L from "leaflet/src/Leaflet.js"; // for debugging

export class LeafletMap extends PolymerElement {
    static get template() {
        return html `
      <link
        rel="stylesheet"
        href=[[leafletCssSrc]]
      />

      <style>
        #map-container {
          width: 100%;
          height: 100%;
        }
        #divMap {
          width: 100%;
          height: 100%;
        }
        .div-icon {
        	background: #fff;
        	border: 1px solid #666;
        	height: auto;
        	width: auto;
        	padding-left: 5px;
        	padding-right: 5px;
        	white-space: nowrap;
        }
      </style>
      <div id="divMap"></div>
    `;
    }

    static get properties() {
        return {
            leafletCssSrc: {
                type: String
            },
            map: {
                type: Object,
                notify: true
            },
            items: {
                type: Array,
                notify: true
            },
            tile: {
                type: Object,
                notify: true
            }
        };
    }

    ready() {
        super.ready();
        const vers = L.version;
        this.leafletCssSrc =  "https://unpkg.com/leaflet@" + vers + "/dist/leaflet.css";
        this._initMap();
    }

    setViewPoint(obj) {
        this.map.setView(obj.point.coordinates, obj.point.zoom);
    }
    
    setTileLayer(layer)
    {
        if (this.tile)
            this.map.removeLayer(this.tile);
        this.tile = L.tileLayer(layer.tile.link,{attribution: layer.tile.attribution, maxZoom: layer.tile.zoom, id: layer.tile.id}).addTo(this.map);
        this.tile.bringToFront();
    }

    setZoomLevel(zoom)
    {
        this.map.setZoom(zoom);
    }

    _initMap() {
        const leafletMapElement = this;
        this.map = new L.map(this.$.divMap);
        this.map.on('moveend', function (e) {
            let center = e.target.getCenter();
            let bounds = e.target.getBounds();
            let northEast = bounds.getNorthEast();
            let northWest = bounds.getNorthWest();
            let southEast = bounds.getSouthEast();
            let southWest = bounds.getSouthWest();

            const customEvent = new CustomEvent('map-leaflet-viewport-move-end', {
                detail: {
                    center: center,
                    northEast: northEast,
                    northWest: northWest,
                    southEast: southEast,
                    southWest: southWest
                }
            });
            leafletMapElement.dispatchEvent(customEvent);
        });

        // mapping of Leaflet items (like features, tile layers, etc.) to IDs for the flow connection
        this.items = new Map();
    }

    addMarker(itemId, obj) {
      const leafletMapElement = this;
        var leafIcon;
        if (obj.properties.icon.type == 'DivIcon') {
            leafIcon = new L.divIcon(obj.properties.icon);
        } else {
            leafIcon = new L.Icon(obj.properties.icon);
        }    
        var item = L.marker(obj.geometry.coordinates, {icon: leafIcon}).addTo(this.map);
        this.items.set(itemId, item);
       
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        item.on('click', function (event) {
            const customEvent = new CustomEvent('map-leaflet-marker-clicked', {
                detail: {
                    mapItemId: itemId,
                    position: event.latlng
                }
            });
            leafletMapElement.dispatchEvent(customEvent);
        });


    }

    deleteItem(itemId) {
        let itemToDelete = this.items.get(itemId);
        itemToDelete.remove();
        this.items.delete(itemId);
    }

    addPolygon(itemId, obj) {
        var item = L.polygon(obj.geometry.coordinates, obj.properties).addTo(this.map);

        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.set(itemId, item);
    }

    addCircle(itemId, obj) {
        var item = L.circle(obj.geometry.coords, obj.properties).addTo(this.map);
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.set(itemId, item);
    }

    addControlLayers(itemId, params) {
        if (!this.tile)
            throw new Error("no tile was set yet");//todo
        let baseLayers = {};
        baseLayers["lay"] = this.tile;
        let layersControlItem = L.control.layers(baseLayers, null, params.options);
        layersControlItem.addTo(this.map);

        this.items.set(itemId, layersControlItem);
    }
}

customElements.define("leaflet-map", LeafletMap);