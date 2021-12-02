package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapSchema<T1> extends BaseSchema<T1> {
    private final Map<String, List<Object>> parameters = new HashMap<>();
    private Set<String> keySet = new HashSet<>();
    private Map<String, BaseSchema<Object>> shapeMap = new HashMap<>();

    public MapSchema() {
    }

    public final MapSchema<T1> required() {
        parameters.put("required", null);
        return this;
    }

    public final MapSchema<T1> sizeof(final int sizeMap) {
        parameters.put("sizeof", List.of(sizeMap));
        return this;
    }

    @Override
    public final Boolean isValid(T1 map) {
        setParameters(parameters);
        parametersIsEmpty();
        final Set<String> parameter = parameters.keySet();
        final List<Boolean> result = new ArrayList<>();
        for (String key : parameter) {
            setParameter(key);
            if (key == null || map == null) {
                result.add(super.isValid(null));
                break;
            }
            switch (key) {
                case "required":
                    result.add(map instanceof Map);
                    break;
                case "sizeof":
                    result.add((map instanceof Map)
                            && ((Map<Object, Object>) map).size() == (int) parameters.get(key).get(0));
                    break;
                case "shape":
                    result.add(isShape((Map<Object, Object>) map));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        if (result.size() == 1) {
            return result.get(0);
        }
        return !result.contains(false);
    }


    public final void shape(final Map<String, BaseSchema<Object>> map) {
        shapeMap = map;
        keySet = map.keySet();
        parameters.put("shape", null);
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

