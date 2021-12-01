package hexlet.code.schemas;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class BaseSchema<T1> {

    private static String parameter;
    private static Map<String, List<Object>> parameters = new HashMap<>();

    public BaseSchema() {
    }

    public static void setParameters(Map<String, List<Object>> param) {
        BaseSchema.parameters = param;
    }

    public static void setParameter(String param1) {
        BaseSchema.parameter = param1;
    }

    /**
     * @param value the value that is passed to the method
     * @return return false or true
     */
    public Boolean isValid(T1 value) {
        if (parameter == null) {
            return true;
        }
        switch (parameter) {
            case "required":
                return value != null;
            case "minLength":
            case "sizeof":
                return (int) parameters.get(parameter).get(0) == 0;
            case "contains":
                return parameters.get(parameter).get(0) == null;
            case "positive":
                return value == null;
            case "range":
                return (int) parameters.get(parameter).get(0) == 0 && (int) parameters.get(parameter).get(1) == 0;
            default:
                throw new RuntimeException();
        }
    }
}
