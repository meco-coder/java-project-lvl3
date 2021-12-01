package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringSchema extends BaseSchema<Object> {

    private final Map<String, List<Object>> parameters = new HashMap<>();

    public StringSchema() {
    }

    public final StringSchema minLength(final int value) {
        parameters.put("minLength", List.of(value));
        return this;
    }

    public final StringSchema required() {
        parameters.put("required", null);
        return this;
    }

    public final StringSchema contains(final String str) {
        parameters.put("contains", List.of(str));
        return this;
    }


    @Override
    public final Boolean isValid(Object value) {
        if (parameters.size() == 0) {
            return true;
        }
        setParameters(parameters);
        final Set<String> parameter = parameters.keySet();
        final List<Boolean> result = new ArrayList<>();
        for (String key : parameter) {
            setParameter(key);
            if (value == null || key == null) {
                result.add(super.isValid(null));
                break;
            }
            switch (key) {
                case "minLength":
                    result.add(((String) value).length() >= (int) parameters.get(key).get(0));
                    break;
                case "required":
                    result.add(((String) value).length() > 0);
                    break;
                case "contains":
                    result.add(((String) value).contains((String) parameters.get(key).get(0)));
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

}
