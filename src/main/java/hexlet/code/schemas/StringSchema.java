package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private static String parameter;
    private static int num;
    private static String strContains;

    public StringSchema(final String sortParameter, final int value) {
        super(sortParameter, value);
        StringSchema.parameter = sortParameter;
        StringSchema.num = value;

    }

    public StringSchema(final String sortParameter, final String stringContains) {
        super(sortParameter, stringContains);
        StringSchema.parameter = sortParameter;
        StringSchema.strContains = stringContains;
    }

    public StringSchema(final String sortParameter) {
        super(sortParameter);
        StringSchema.parameter = sortParameter;
    }




    @Override
    public final Boolean isValid(Object value) {
        if (value == null || parameter == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "minLength":
                return ((String) value).length() >= num;
            case "required":
                return ((String) value).length() > 0;
            case "contains":
                return ((String) value).contains(strContains);
            default:
                throw new RuntimeException();
        }
    }


    public final StringSchema minLength(final int value) {
        return new StringSchema("minLength", value);
    }

    public final StringSchema required() {
        return new StringSchema("required");
    }

    public final StringSchema contains(final String str) {
        return new StringSchema("contains", str);
    }

}
