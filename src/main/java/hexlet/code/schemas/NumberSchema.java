package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumberSchema extends BaseSchema<Object> {
    private final Map<String, List<Object>> parameters = new HashMap<>();

    public NumberSchema() {
    }

    public final NumberSchema range(final Integer minNum, final Integer maxNum) {
        parameters.put("range", List.of(minNum, maxNum));
        return this;
    }

    public final NumberSchema required() {
        parameters.put("required", null);
        return this;
    }

    public final NumberSchema positive() {
        parameters.put("positive", null);
        return this;
    }

    @Override
    public final Boolean isValid(final Object value) {
        setParameters(parameters);
        parametersIsEmpty();
        final Set<String> parameter = parameters.keySet();
        final List<Boolean> result = new ArrayList<>();
        for (String key : parameter) {
            setParameter(key);
            if (value == null || key == null) {
                result.add(super.isValid(value));

            } else {
                switch (key) {
                    case "required":
                        result.add(value instanceof Integer);
                        break;
                    case "positive":
                        if (value instanceof Integer) {
                            result.add((int) value > 0);
                        }
                        break;
                    case "range":
                        if (value instanceof Integer) {
                            result.add(((int) value >= (int) parameters.get(key).get(0)
                                    && (int) value <= (int) parameters.get(key).get(1)));
                        }
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        }
        return !result.contains(false);
    }

}
