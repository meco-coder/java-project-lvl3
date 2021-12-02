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
        final List<Boolean> validationResult = new ArrayList<>();
        for (String parameterFromSet : parameter) {
            setParameter(parameterFromSet);
            if (value == null || parameterFromSet == null) {
                validationResult.add(super.isValid(value));
            } else if (value instanceof Integer) {
                switch (parameterFromSet) {
                    case "required":
                        validationResult.add(true);
                        break;
                    case "positive":
                        validationResult.add((int) value > 0);
                        break;
                    case "range":
                        validationResult.add(((int) value >= (int) parameters.get(parameterFromSet).get(0)
                                && (int) value <= (int) parameters.get(parameterFromSet).get(1)));
                        break;
                    default:
                        throw new RuntimeException();
                }
            } else {
                validationResult.add(false);
            }
        }
        return !validationResult.contains(false);
    }

}
