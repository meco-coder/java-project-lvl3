package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Object> {
    private static String parameter;
    private static Integer min;
    private static Integer max;

    public NumberSchema(String parameter, Integer min, Integer max) {
        super(parameter, min, max);
        NumberSchema.parameter = parameter;
        NumberSchema.min = min;
        NumberSchema.max = max;
    }
    public NumberSchema(String parameter) {
        super(parameter);
        NumberSchema.parameter = parameter;
    }


    public NumberSchema range(Integer min, Integer max) {
        return new NumberSchema("range", min, max);
    }

    public NumberSchema required() {
        return new NumberSchema("required");
    }

    public NumberSchema positive() {
        return new NumberSchema("positive");
    }

    public Boolean isValid(Object value) {
        if (value == null || parameter == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "required":
                return value instanceof Number;
            case "positive":
                return (int) value >= 0;
            case "range":
                return ((int) value >= min && (int) value <= max);
            default:
                throw new RuntimeException();
        }
    }

}
