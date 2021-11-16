package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
public class StringSchema extends BaseSchema {
    private static String parameter = null;
    private static int num;
    private static String strContains;

    public StringSchema(String option, int num) {
        this.parameter = option;
        this.num = num;
    }

    public StringSchema(String option, String strContains) {
        this.parameter = option;
        this.strContains = strContains;
    }

    public StringSchema(String option) {
        this.parameter = option;
    }

    public StringSchema() {
    }

    public static Boolean isValid(String str) {
        if (parameter == null) {
            return true;
        }
        switch (parameter) {
            case "minLength":
                if (Objects.equals(str, null) && num == 0) {
                    return true;
                } else if (Objects.equals(str, null)) {
                    return false;
                }
                return str.length() >= num;
            case "required":
                if (Objects.equals(str, null)) {
                    return false;
                }
                return str.length() != 0;
            case "contains":
                if (Objects.equals(str, null) && Objects.equals(strContains, null)) {
                    return true;
                }
                return str.contains(strContains);
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
