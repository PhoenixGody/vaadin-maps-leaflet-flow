//package software.xdev.vaadin.maps.leaflet.flow.util;
//
//import elemental.json.Json;
//import elemental.json.JsonObject;
//import elemental.json.JsonValue;
//
//public class JsonUtil {
//    private JsonUtil() {
//        //no instance
//    }
//
//    public static JsonObject mergeJsonObjects(JsonObject ...pJsonObjects)
//    {
//        final JsonObject result = Json.createObject();
//        for (JsonObject object : pJsonObjects) {
//            for (String key : object.keys()) {
//                if (result.hasKey(key))
//                    throw new RuntimeException("element already exists");//todo
//                result.put(key, (JsonValue) object.get(key));
//            }
//        }
//        return result;
//    }
//
//}
