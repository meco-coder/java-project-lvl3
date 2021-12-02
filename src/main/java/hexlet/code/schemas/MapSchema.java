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
        final List<Boolean> validationResult = new ArrayList<>();
        for (String parameterFromSet : parameter) {
            setParameter(parameterFromSet);
            if (parameterFromSet == null || map == null) {
                validationResult.add(super.isValid(null));
                break;
            }
            switch (parameterFromSet) {
                case "required":
                    validationResult.add(map instanceof Map);
                    break;
                case "sizeof":
                    validationResult.add((map instanceof Map)
                            && ((Map<?, ?>) map).size() == (int) parameters.get(parameterFromSet).get(0));
                    break;
                case "shape":
                    validationResult.add(isShape((Map<Object, Object>) map));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return !validationResult.contains(false);
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

