package kr.hhplus.be.global.error;

public class AppSystemException extends RuntimeException {

    public AppSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
