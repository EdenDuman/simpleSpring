package simpe.spring.exceptions;

public class ValidationParamException extends Exception {
    public ValidationParamException(String paramName) {
        super("The " + paramName + " is missing.");
    }
}
