package ru.evgeny.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("within(ru.evgeny..*)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Call method : " + joinPoint.getSignature().getName());
    }

    @After("within(ru.evgeny..*)")
    public void logAfterReturning(JoinPoint joinPoint) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " finish");

    }
}
