package ir.hosseindn.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AopLogging {

    @Pointcut("execution(* ir.hosseindn.service.*.*.*(..))")
    public void loggingAllServiceMethod() {
    }

    @Around("loggingAllServiceMethod()")
    public Object loggingAllServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Executing method: \n {}.{}", proceedingJoinPoint.getTarget(),proceedingJoinPoint.getSignature().getName());
        Object proceed = proceedingJoinPoint.proceed();
        log.info("Method {} executed successfully", proceedingJoinPoint.getSignature().getName());
        return proceed;
    }
}
