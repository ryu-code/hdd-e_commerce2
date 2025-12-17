package kr.hhplus.be.global.error;

public abstract class ErrorException extends RuntimeException {

    public ErrorException(String message) {
        super(message);
    }

    public ErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
