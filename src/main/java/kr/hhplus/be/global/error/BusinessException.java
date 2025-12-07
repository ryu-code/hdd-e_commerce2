package kr.hhplus.be.global.error;

public class BusinessException {

    // 입력값 null
    public static class InputDataNullException extends ErrorException {
        public InputDataNullException() {
            super("입력값이 null 입니다.");
        }
    }

    // 잘못된 입력값
    public static class InvalidValueException extends ErrorException {
        public InvalidValueException() {
            super("입력값이 유효하지 않습니다.");
        }
    }

    // 재고 부족
    public static class CantNotPurchaseException extends ErrorException {
        public CantNotPurchaseException(String message) {
            super(message);
        }
    }

    // 충전 불가
    public static class CantNotChargeException extends ErrorException {
        public CantNotChargeException(String message) {
            super(message);
        }
    }
}
