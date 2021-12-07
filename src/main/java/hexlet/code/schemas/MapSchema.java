package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class MapSchema<T1> extends BaseSchema<T1> {
    public MapSchema() {
    }

    public final MapSchema<T1> required() {
        final Predicate<Object> isRequired = x -> x instanceof Map;
        setPredicates(isRequired);
        return this;
    }

    public final MapSchema<T1> sizeof(final int sizeMap) {
        final Predicate<Object> sizeof = x -> {
            if (x instanceof Map) {
                return ((Map<?, ?>) x).size() == sizeMap;
            }
            return false;
        };
        setPredicates(sizeof);
        return this;
    }

    public final void shape(final Map<String, BaseSchema<Object>> map) {
        final Predicate<Object> shape = x -> {
            List<Boolean> resultShape = new ArrayList<>();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                resultShape.add(map.get(key).isValid(((Map<?, ?>) x).get(key)));
            }
            return !resultShape.contains(false);
        };
        setPredicates(shape);
    }
}

