package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Object> {
    private static String parameter;
    private static Integer min;
    private static Integer max;

    public NumberSchema(final String sortParameter, final Integer minNum, final Integer maxNum) {
        super(sortParameter, minNum, maxNum);
        NumberSchema.parameter = sortParameter;
        NumberSchema.min = minNum;
        NumberSchema.max = maxNum;
    }

    public NumberSchema(final String sortParameter) {
        super(sortParameter);
        NumberSchema.parameter = sortParameter;
    }


    public final NumberSchema range(final Integer minNum, final Integer maxNum) {
        return new NumberSchema("range", minNum, maxNum);
    }

    public final NumberSchema required() {
        return new NumberSchema("required");
    }

    public final NumberSchema positive() {
        return new NumberSchema("positive");
    }

    public final Boolean isValid(final Object value) {
        if (value == null || parameter == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "required":
                return value instanceof Integer;
            case "positive":
                return (int) value >= 0;
            case "range":
                return ((int) value >= min && (int) value <= max);
            default:
                throw new RuntimeException();
        }
    }

}
