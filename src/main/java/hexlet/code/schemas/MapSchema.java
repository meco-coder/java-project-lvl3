package hexlet.code.schemas;

import java.util.Map;

public class MapSchema<T1> extends BaseSchema<T1> {
    private static String parameter;
    private static int sizeMap;

    public MapSchema(String parameter) {
        super(parameter);
        MapSchema.parameter = parameter;
    }

    public MapSchema(String parameter, int sizeMap) {
        super(parameter,sizeMap);
        MapSchema.parameter = parameter;
        MapSchema.sizeMap = sizeMap;
    }

    public MapSchema<T1> required() {
        return new MapSchema<>("required");
    }

    public MapSchema<T1> sizeof(int sizeMap) {
        return new MapSchema<>("sizeof", sizeMap);

    }

    public Boolean isValid(T1 map) {
        if (parameter == null || map == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "required":
                return map instanceof Map;
            case "sizeof":
                return (map instanceof Map) && ((Map<?, ?>) map).size() == sizeMap;
            default:
                throw new RuntimeException();
        }
    }
}
