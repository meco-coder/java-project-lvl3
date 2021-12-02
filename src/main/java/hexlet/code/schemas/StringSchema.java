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

    public final StringSchema minLength(final int stringLenght) {
        parameters.put("minLength", List.of(stringLenght));
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
        setParameters(parameters);
        parametersIsEmpty();
        final Set<String> parameter = parameters.keySet();
        final List<Boolean> validationResult = new ArrayList<>();
        for (String parameterFromSet : parameter) {
            setParameter(parameterFromSet);
            if (value == null || parameterFromSet == null) {
                validationResult.add(super.isValid(null));
                break;
            }
            switch (parameterFromSet) {
                case "minLength":
                    validationResult.add(((String) value).length() >= (int) parameters.get(parameterFromSet).get(0));
                    break;
                case "required":
                    validationResult.add(((String) value).length() > 0);
                    break;
                case "contains":
                    validationResult.add(((String) value).contains((String) parameters.get(parameterFromSet).get(0)));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return !validationResult.contains(false);
    }

}
