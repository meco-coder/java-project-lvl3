package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StringSchema extends BaseSchema<Object> {
    private static String parameter;
    private static int num;
    private static String strContains;

    public StringSchema(String parameter, int num) {
        super(parameter, num);
        StringSchema.parameter = parameter;
        StringSchema.num = num;

    }

    public StringSchema(String parameter, String strContains) {
        super(parameter, strContains);
        StringSchema.parameter = parameter;
        StringSchema.strContains = strContains;
    }

    public StringSchema(String parameter) {
        super(parameter);
        StringSchema.parameter = parameter;
    }

    public Boolean isValid(String value) {
        if (value == null || parameter == null) {
            return super.isValid(null);
        }
        switch (parameter) {
            case "minLength":
                return value.length() >= num;
            case "required":
                return value.length() > 0;
            case "contains":
                return value.contains(strContains);
            default:
                throw new RuntimeException();
        }
    }


    public StringSchema minLength(int num) {
        return new StringSchema("minLength", num);
    }

    public StringSchema required() {
        return new StringSchema("required");
    }

    public StringSchema contains(String str) {
        return new StringSchema("contains", str);
    }

}
