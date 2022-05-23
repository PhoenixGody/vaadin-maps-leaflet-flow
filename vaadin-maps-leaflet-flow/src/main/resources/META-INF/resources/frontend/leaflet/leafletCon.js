import {html,PolymerElement} from "@polymer/polymer/polymer-element.js";
//import "leaflet/dist/leaflet.js"; // for distribution
import * as L from "leaflet/src/Leaflet.js";
import {Handler} from "leaflet/dist/leaflet-src.esm"; // for debugging

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
        	height: auto;
        	width: auto;
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
    
    setTileLayer(layerItemId)
    {
        if (this.activeTile != null)
            this.activeTile.remove();

        if (!this.tiles.has(layerItemId))
            throw new Error("target active TileLayer was not found in the component items - has it not been added yet?");

        let tileLayerItem = this.tiles.get(layerItemId);
        this.activeTile = tileLayerItem;
        tileLayerItem.addTo(this.map);
        this.tiles.get(layerItemId).bringToFront();
    }

    setZoomLevel(zoom)
    {
        this.map.setZoom(zoom);
    }

    _initMap() {
        const leafletMapElement = this;
        this.map = new L.map(this.$.divMap);
        // this is a featuregroup that contains alls features that are relevant for being able to zoom to the content
        // note that the features area also added to the map but the zoomToContentFeautreGroup is not.
        // This is intended as we just need this featureGroup for getting the bounds of the added features.
        // It would be even better if there was a flow-counterpart to adding custom featureGroups that allow then
        // zooming to the content.
        this.zoomToContentFeautreGroup = L.featureGroup();

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

        this.map.on('locationfound', function (e) {
            const customEvent = new CustomEvent('map-leaflet-locationfound', {
                detail: {
                    latlng: e.latlng,
                    accuracy: e.accuracy,
                    altitude: e.altitude,
                    altitudeAccuracy: e.altitudeAccuracy,
                    heading: e.heading,
                    speed: e.speed,
                    timestamp: e.timestamp,
                    northEast: e.bounds.getNorthEast(),
                    northWest: e.bounds.getNorthWest(),
                    southEast: e.bounds.getSouthEast(),
                    southWest: e.bounds.getSouthWest()
                }
            });
            leafletMapElement.dispatchEvent(customEvent);
        });

        this.map.on('locationerror', function (e) {
            const customEvent = new CustomEvent('map-leaflet-locationerror', {
                detail: {
                    message: e.message,
                    code: e.code
                }
            });
            leafletMapElement.dispatchEvent(customEvent);
        });

        // mapping of Leaflet items (like features, etc.) to IDs for the flow connection
        this.items = new Map();//todo cleanup on disconnect or call from server?
        // mapping of Leaflet TileLayer items to IDs for the flow connection
        this.tiles = new Map();//todo cleanup on disconnect or call from server?
        // this is the currently active tileLayer:
        this.activeTile = null;
    }

    addMarker(itemId, obj) {
      const leafletMapElement = this;
        var leafIcon;
        if (obj.properties.icon.type == 'DivIcon') {
            leafIcon = new L.divIcon(obj.properties.icon);
        } else {
            leafIcon = new L.icon(obj.properties.icon);
        }    
        var item = L.marker(obj.geometry.coordinates, {icon: leafIcon});
        item.addTo(this.map);
        this.zoomToContentFeautreGroup.addLayer(item);

        this.items.set(itemId, item);

        // only for deprecated calls; there exists a bindPopup function that is better suited
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
        let latlngs = this._extractCoordinates(obj);

        var item = L.polygon(latlngs, obj.properties);
        item.addTo(this.map);
        this.zoomToContentFeautreGroup.addLayer(item);

        // only for deprecated calls; there exists a bindPopup function that is better suited
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.set(itemId, item);
    }

    addCircle(itemId, obj) {
        var item = L.circle(obj.geometry.coords, obj.properties);
        item.addTo(this.map);
        this.zoomToContentFeautreGroup.addLayer(item);

        // only for deprecated calls; there exists a bindPopup function that is better suited
        if (obj.properties.popup != null) {
            item.bindPopup(obj.properties.popup);
        }

        this.items.set(itemId, item);
    }

    addControlLayers(itemId, params) {
        let baseLayers = params.baseConfig;
        for (let name in baseLayers){
            // replace the id with the corresponding item
            baseLayers[name] = this.tiles.get(baseLayers[name]);
            if (!baseLayers[name]){
                throw new Error("TileLayer was not found in the component items - has it not been added?");
            }
        }
        let layersControlItem = L.control.layers(baseLayers, null, params.options);
        layersControlItem.addTo(this.map);

        this.items.set(itemId, layersControlItem);
    }

    onTileLayerAdded(event)
    {
        this.activeTile = event.target;
    }

    onTileLayerRemoved(event)
    {
        this.activeTile = null;
    }

    addTileLayer(itemId, layer) {
        let item = L.tileLayer(layer.tile.link,{attribution: layer.tile.attribution, maxZoom: layer.tile.zoom, id: layer.tile.id});

        item.on('add', this.onTileLayerAdded, this);
        item.on('remove', this.onTileLayerRemoved, this);

        this.tiles.set(itemId, item);
    }

    removeTileLayer(itemId) {
        let item = this.tiles.get(itemId);
        if (this.activeTile === item)
            item.remove();

        item.off('add', this.onTileLayerAdded, this);
        item.off('remove', this.onTileLayerRemoved, this);
        this.tiles.delete(itemId);
    }

    bindTooltip(layerItemId, params) {
        let layer = this.items.get(layerItemId);
        if (!layer)
            throw new Error("Tooltip could not be added: the itemId of the target layer was not found. " +
                "Maybe the Layer is not yet added?");

        layer.bindTooltip(params.tooltipContent, params.tooltipOptions);
    }

    unbindTooltip(layerItemId) {
        let layer = this.items.get(layerItemId);
        if (!layer)
            throw new Error("Tooltip could not be added: the itemId of the target layer was not found. " +
                "Maybe the Layer is not yet added?");

        layer.unbindTooltip();
    }


    bindPopup(layerItemId, params) {
        let layer = this.items.get(layerItemId);
        if (!layer)
            throw new Error("Tooltip could not be added: the itemId of the target layer was not found. " +
                "Maybe the Layer is not yet added?");

        layer.bindPopup(params.popupContent, params.popupOptions);
    }

    unbindPopup(layerItemId) {
        let layer = this.items.get(layerItemId);
        if (!layer)
            throw new Error("Tooltip could not be added: the itemId of the target layer was not found. " +
                "Maybe the Layer is not yet added?");

        layer.unbindPopup();
    }

    getItem(itemId) {
        let result = this.items.get(itemId);
        if (!result)
            throw new Error(`requested item with id: "${itemId}" could not be found.`);
        return result;
    }

    startLocate(options) {
        this.map.locate(options);
    }

    stopLocate() {
        this.map.stopLocate();
    }

    zoomToContent() {
        let latlngbounds = this.zoomToContentFeautreGroup.getBounds();
        this.map.fitBounds(latlngbounds);
    }

    /**
     * extracts the coordinates from a given parameter object. This parameter object is populated by the flow side API.
     * @param {Object} objWithCoords object with eiter a geometry or geoJson element that contains coordinate information
     * @returns {Array} LatLngs as an array
     * @private
     */
    _extractCoordinates(objWithCoords) {
        let latlngs = null;
        if (objWithCoords.geometry && objWithCoords.geometry.coordinates)
            latlngs = objWithCoords.geometry.coordinates;
        else if (objWithCoords.geoJson && objWithCoords.geoJson.coords && objWithCoords.geoJson.levelsDeep != null)
        {
            if (typeof(objWithCoords.geoJson.coords) === "string")
                latlngs = L.GeoJSON.coordsToLatLngs(JSON.parse(objWithCoords.geoJson.coords), objWithCoords.geoJson.levelsDeep);
            else
                latlngs = L.GeoJSON.coordsToLatLngs(objWithCoords.geoJson.coords, objWithCoords.geoJson.levelsDeep);
        }

        if (latlngs === null)
            throw new Error("No coordinates were provided to extract.");

        return latlngs;
    }
}

customElements.define("leaflet-map", LeafletMap);