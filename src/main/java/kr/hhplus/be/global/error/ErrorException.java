package kr.hhplus.be.global.error;

public class ErrorException {

    public static Exception inputDataNullException;
    public static Exception invalidValueException;

    public static class inputDataNullException extends RuntimeException {
        public String inputDataNullException() {
            return "입력값이 null 입니다.";
        }
    }

    public static class invalidValueException extends RuntimeException {
        public String invalidValueException() {
            return "입력값이 유효하지 않습니다.";
        }
    }

    public static class CantNotPurchaseException extends RuntimeException {
        public CantNotPurchaseException(String message) {
            super(message);
        }
    }

    public static class CantNotChargeException extends RuntimeException {
        public CantNotChargeException(String message) {
            super(message);
        }
    }
}
