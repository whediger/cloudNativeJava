package com.example.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggingAroundAspect {

    private Log log = LogFactory.getLog(getClass());

    @Around("exicution(* com.example.boot.CustomerService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws throwable {
        LocalDateTime start = LocalDateTime.now();

        Throwable toThrow = null;
        Object returnValue = null;

        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable t){
            toThrow = t;
        }
        LocalDateTime stop = LocalDateTime.now();

        log.info("starting @ " + start.toString());
        log.info("finishing @ " + stop.toString() + " with duration "
                + stop.minusNanos(start.getNano()).getNano());

        if(null != toThrow) {
            throw toThrow;

            return returnValue;
        }
    }

}
