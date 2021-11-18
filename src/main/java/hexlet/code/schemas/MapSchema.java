package hexlet.code.schemas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapSchema<T1> extends BaseSchema<T1> {
    private static String parameter;
    private static int sizeMap;
    private static Set<String> keySet = new HashSet<>();
    private static Map<String, BaseSchema<Object>> shapeMap = new HashMap<>();

    public MapSchema(String parameter) {
        super(parameter);
        MapSchema.parameter = parameter;
    }

    public MapSchema(String parameter, int sizeMap) {
        super(parameter, sizeMap);
        MapSchema.parameter = parameter;
        MapSchema.sizeMap = sizeMap;
    }

    public MapSchema<T1> required() {
        return new MapSchema<>("required");
    }

    public MapSchema<T1> sizeof(int sizeMap) {
        return new MapSchema<>("sizeof", sizeMap);

    }

    public Boolean isValid(Map<Object, Object> map) {
        if (parameter == null || map == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "required":
                return map instanceof Map;
            case "sizeof":
                return (map instanceof Map) &&  map.size() == sizeMap;
            case "shape":
                return isShape(map);
            default:
                throw new RuntimeException();
        }
    }

    public void shape(Map<String, BaseSchema<Object>> map) {
        shapeMap = map;
        keySet = map.keySet();
        parameter = "shape";
    }

    public Boolean isShape(Map<Object, Object> map) {
        int result = 0;
        for (String key : keySet) {
            if (shapeMap.get(key).isValid(map.get(key))) {
                result = result + 1;
            }
        }
        return result == 2;
    }
}
