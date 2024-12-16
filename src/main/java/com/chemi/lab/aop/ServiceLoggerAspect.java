package com.chemi.lab.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Aspect
@Component
@Slf4j
public class ServiceLoggerAspect {

//    private final HttpServletRequest request;
//
//    public ServiceLoggerAspect(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    @Pointcut("execution(* com.chemi.lab.*.*ervice.*(..))")
//    private void publicServiceFromLoggingPackage() {
//    }
//
//    @Before(value = "publicServiceFromLoggingPackage()")
//    public void logBeforeMethodExecution(JoinPoint joinPoint) {
//        log.info("Enter {}() method with params {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//    }

//    @AfterReturning(value = "publicServiceFromLoggingPackage()", returning = "result")
//    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
//        log.info("Completed {}() method with return value of  {}", joinPoint.getSignature().getName(), result.toString());
//    }
}



