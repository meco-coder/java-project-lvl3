package hexlet.code.schemas;

public class BaseSchema<T1> {
    private static String parameter;
    private static int num;
    private static String strContains;
    private static int min;
    private static int max;


    public BaseSchema(String parameter, int num) {
        BaseSchema.parameter = parameter;
        BaseSchema.num = num;
    }

    public BaseSchema(String parameter, String strContains) {
        BaseSchema.parameter = parameter;
        BaseSchema.strContains = strContains;
    }

    public BaseSchema(String parameter) {
        BaseSchema.parameter = parameter;
    }

    public BaseSchema(String parameter, int min, int max) {
        BaseSchema.parameter = parameter;
        BaseSchema.min = min;
        BaseSchema.max = max;
    }

    public Boolean isValid(T1 value) {
        if (parameter == null) {
            return true;
        }
        switch (parameter) {
            case "required":
                return value != null;
            case "minLength":
            case "sizeof":
                return num == 0;
            case "contains":
                return strContains == null;
            case "positive":
                return value == null;
            case "range":
                return min == 0 && max == 0;
            default:
                throw new RuntimeException();
        }

    }
}
