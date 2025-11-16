package kr.hhplus.be.global.error;

public class ErrorException {

    public static Exception inputDataNullException;

    public static class inputDataNullException extends RuntimeException {
        public String inputDataNullException() {
            return "입력값이 null 입니다.";
        }
    }
}
