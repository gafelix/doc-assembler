package br.com.looplex.docassembler.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* br.com.looplex.docassembler.exceptions.handler..*(..)) && args(exception)")
    public void logExceptionHandlers(Exception exception) {}

    @Before("logExceptionHandlers(exception)")
    public void logBefore(JoinPoint joinPoint, Exception exception) {
        log.error(exception.getMessage());
    }

}