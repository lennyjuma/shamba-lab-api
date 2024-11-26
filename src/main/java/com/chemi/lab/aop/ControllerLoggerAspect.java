package com.chemi.lab.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
//@Slf4j
public class ControllerLoggerAspect {

    private static final Logger log = LogManager.getLogger(ControllerLoggerAspect.class);
    private final HttpServletRequest request;

    public ControllerLoggerAspect(HttpServletRequest request) {
        this.request = request;
    }
    @Pointcut("execution(* com.chemi.lab.*.*ontroller.*(..))")
    private void publicControllerFromLoggingPackage() {
    }


    @Before(value = "publicControllerFromLoggingPackage()") // Pointcut for all methods in classes ending with "Service"
    public void logRequestInfo(JoinPoint joinPoint) {
        // Get the method signature
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Executing method: {}", methodName);

        // Log the request path
        String requestPath = request.getRequestURI();
        log.info("Request Path: {} with HTTP method {}", requestPath , request.getMethod());

        // Log headers
        AtomicReference<String> headers = new AtomicReference<>("");
        request.getHeaderNames().asIterator().forEachRemaining(header -> {
                    headers.set(headers.get() + "\n" +  header + " -- " + request.getHeader(header));
                }
        );
        log.debug(" \n HEADERS {}",headers);

        // Log correlation ID
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = "Not provided";
        }
        log.info("Correlation ID: {}", correlationId);

        // Log method arguments
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            log.info("Method Arguments:");
//            for (Object arg : args) {
//                log.info("  - {}",arg);
//            }
//        } else {
//            log.info("No arguments passed.");
//        }
    }
}



