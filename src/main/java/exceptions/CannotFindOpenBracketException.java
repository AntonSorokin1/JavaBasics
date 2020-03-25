package exceptions;

public class CannotFindOpenBracketException extends Exception {
    public CannotFindOpenBracketException() {

    }

    public CannotFindOpenBracketException(String message) {
        super(message);
    }
}
