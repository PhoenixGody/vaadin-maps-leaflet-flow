import {html,PolymerElement} from "@polymer/polymer/polymer-element.js";
import "leaflet/dist/leaflet.js"; // for distribution
//import * as L from "leaflet/src/Leaflet.js"; // for debugging

export class LeafletMap extends PolymerElement {
    static get template() {
        return html `
      <link
        rel="stylesheet"
        href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css"
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
        super.ready();
        this.map = new L.map(this.$.divMap);

        var vaadinServer = this.$server;
        this.map.on('moveend', function (e) {
            var center = e.target.getCenter();
            var bounds = e.target.getBounds();
            var northEast = bounds.getNorthEast();
            var northWest = bounds.getNorthWest();
            var southEast = bounds.getSouthEast();
            var southWest = bounds.getSouthWest();
            vaadinServer.onMapMoveEnd(center.lat, center.lng,
                                      northEast.lat, northEast.lng,
                                      northWest.lat, northWest.lng,
                                      southEast.lat, southEast.lng,
                                      southWest.lat, southWest.lng);
        });

        this.items = new Array();
    }

    addMarker(obj) {
      let leafletMapElement = this;
        var leafIcon;
        if (obj.properties.icon.type == 'DivIcon') {
            leafIcon = new L.divIcon(obj.properties.icon);
        } else {
            leafIcon = new L.Icon(obj.properties.icon);
        }    
        var item = L.marker(obj.geometry.coordinates, {icon: leafIcon}).addTo(this.map);
       
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        item.on('click', function (event) {
            let position = obj.geometry.coordinates;
            const customEvent = new CustomEvent('map-leaflet-marker-clicked', {
                detail: {
                    mapElementId: obj.mapElementId,
                    position: position
                }
            });
            leafletMapElement.dispatchEvent(customEvent);
        });

        this.items.push(item);
    }

    deleteItem(index) {
        var delItem = this.items[index];
        delItem.remove();
        this.items.splice(index, 1);
    }

    addPolygon(obj) {
        var item = L.polygon(obj.geometry.coordinates, obj.properties).addTo(
            this.map
        );

        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.push(item);
    }

    addCircle(obj) {
        var item = L.circle(obj.geometry.coords, obj.properties).addTo(this.map);
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.push(item);
    }
}

customElements.define("leaflet-map", LeafletMap);