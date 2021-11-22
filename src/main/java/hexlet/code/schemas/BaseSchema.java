package hexlet.code.schemas;

import lombok.Data;

@Data
public class BaseSchema<T1> {

    private static String parameter;
    private static int num;
    private static String strContains;
    private static int min;
    private static int max;


    public BaseSchema(String sortParameter, int value) {
        BaseSchema.parameter = sortParameter;
        BaseSchema.num = value;
    }

    public BaseSchema(String sortParameter, String stringContains) {
        BaseSchema.parameter = sortParameter;
        BaseSchema.strContains = stringContains;
    }

    public BaseSchema(String sortParameter) {
        BaseSchema.parameter = sortParameter;
    }

    public BaseSchema(String sortParameter, int minNum, int maxNum) {
        BaseSchema.parameter = sortParameter;
        BaseSchema.min = minNum;
        BaseSchema.max = maxNum;
    }

    /**
     * @param value the value that is passed to the method
     * @return return false or true
     */
    public  Boolean isValid(T1 value) {
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
