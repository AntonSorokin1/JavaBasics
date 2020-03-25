package exceptions;

public class CannotFindCloseBracketException extends Exception {
    public CannotFindCloseBracketException() {

    }

    public CannotFindCloseBracketException(String message) {
        super(message);
    }
}
