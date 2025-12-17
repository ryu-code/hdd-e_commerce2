package kr.hhplus.be.global.error;

import jakarta.transaction.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class GlobalExceptionAop {

    @Around("execution(* kr.hhplus.be.server..*(..))")
    public Object errorHandler(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {

            // ✅ Root Cause 추적
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }

            log.error("[SYSTEM ERROR]", root);

            // ✅ 비즈니스 예외는 그대로 던짐 (중요)
            if (root instanceof ErrorException) {
                throw root;
            }

            // ✅ 나머지는 시스템 예외로 변환
            throw new AppSystemException("SYSTEM_ERROR", root);

        }
    }
}
