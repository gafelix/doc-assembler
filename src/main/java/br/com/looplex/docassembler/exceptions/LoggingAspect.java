package br.com.looplex.docassembler.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* br.com.looplex.docassembler.exceptions.handler..*(..)) && args(exception)")
    public void logBefore(JoinPoint joinPoint, Exception exception) {
        log.error(exception.getMessage());
    }

}