package exceptions;

public class ForbiddenSymbolsException extends Exception {
    public ForbiddenSymbolsException() {

    }

    public ForbiddenSymbolsException(String message) {
        super(message);
    }
}
