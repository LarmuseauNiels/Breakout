package be.brickrevolution.util;

public class BreakoutException
        extends RuntimeException {

    public BreakoutException() {
    }

    public BreakoutException(String message) {
        super(message);
    }

    public BreakoutException(String message, Exception innerException) {
        super(message, innerException);
    }
}
