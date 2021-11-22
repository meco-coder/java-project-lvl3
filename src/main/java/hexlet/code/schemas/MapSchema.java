package hexlet.code.schemas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapSchema<T1> extends BaseSchema<T1> {
    private static String parameter;
    private static int size;
    private static Set<String> keySet = new HashSet<>();
    private static Map<String, BaseSchema<Object>> shapeMap = new HashMap<>();

    public MapSchema(final String sortParameter) {
        super(sortParameter);
        MapSchema.parameter = sortParameter;
    }

    public MapSchema(final String sortParameter, final int sizeMap) {
        super(sortParameter, sizeMap);
        MapSchema.parameter = sortParameter;
        MapSchema.size = sizeMap;
    }

    public final MapSchema<T1> required() {
        return new MapSchema<>("required");
    }

    public final MapSchema<T1> sizeof(final int sizeMap) {
        return new MapSchema<>("sizeof", sizeMap);

    }

    @Override
    public final Boolean isValid(T1 map) {
        if (parameter == null || map == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "required":
                return map instanceof Map;
            case "sizeof":
                return (map instanceof Map) && ((Map<Object, Object>) map).size() == size;
            case "shape":
                return isShape((Map<Object, Object>) map);
            default:
                throw new RuntimeException();
        }
    }


    public final void shape(final Map<String, BaseSchema<Object>> map) {
        shapeMap = map;
        keySet = map.keySet();
        parameter = "shape";
    }

    public final Boolean isShape(final Map<Object, Object> map) {
        int result = 0;
        for (String key : keySet) {
            if (shapeMap.get(key).isValid(map.get(key))) {
                result = result + 1;
            }
        }
        return result == 2;
    }

}
