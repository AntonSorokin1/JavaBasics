package exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {

    }

    public DivisionByZeroException(String message) {
        super(message);
    }
}
