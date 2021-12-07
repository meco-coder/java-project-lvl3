package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class MapSchema<T1> extends BaseSchema<T1> {
    private Set<String> keySet = new HashSet<>();
    private Map<String, BaseSchema<Object>> shapeMap = new HashMap<>();

    public MapSchema() {
    }

    public final MapSchema<T1> required() {
        Predicate<Object> isRequired = x -> x instanceof Map;
        setPredicates(isRequired);
        return this;
    }

    public final MapSchema<T1> sizeof(final int sizeMap) {
        Predicate<Object> sizeofIsValid = x -> {
            if (x instanceof Map) {
                return ((Map<?, ?>) x).size() == sizeMap;
            }
            return false;
        };
        setPredicates(sizeofIsValid);
        return this;
    }

    public final void shape(final Map<String, BaseSchema<Object>> map) {
        shapeMap = map;
        keySet = map.keySet();
        Predicate<Object> shape = x -> {
            List<Boolean> resultShape = new ArrayList<>();
            for (String key : keySet) {
                resultShape.add(shapeMap.get(key).isValid(((Map<?, ?>) x).get(key)));
            }
            return !resultShape.contains(false);
        };
        setPredicates(shape);
    }
}

