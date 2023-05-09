package com.beauty_project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.beauty_project.*.*.*(..)) && !@annotation(com.beauty_project.aspect.NoLogging)")
    public Object logsAroundMethodsExecution(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().toString();
        log.info(String.format("Method execution started: %s, with parameters: %s", methodName, Arrays.toString(args)));
        Object proceed = joinPoint.proceed();
        log.info(String.format("Method execution finished: %s, with parameters: %s", methodName, Arrays.toString(args)));
        return proceed;
    }
}