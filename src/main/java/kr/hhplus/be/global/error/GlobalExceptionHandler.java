package kr.hhplus.be.global.error;

import jakarta.transaction.SystemException;
import kr.hhplus.be.global.error.BusinessException.CantNotChargeException;
import kr.hhplus.be.global.error.BusinessException.CantNotPurchaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ 재고 부족
    @ExceptionHandler(CantNotPurchaseException.class)
    public ResponseEntity<?> handleStock(CantNotPurchaseException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // ✅ 충전 실패
    @ExceptionHandler(CantNotChargeException.class)
    public ResponseEntity<?> handleCharge(CantNotChargeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // ✅ 시스템 예외
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<?> handleSystem(SystemException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
