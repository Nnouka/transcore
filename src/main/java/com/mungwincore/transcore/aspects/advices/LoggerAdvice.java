package com.mungwincore.transcore.aspects.advices;

import com.mungwincore.transcore.aspects.annotations.Loggable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Aspect
@Component
public class LoggerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);
    @Before("@annotation(com.mungwincore.transcore.aspects.annotations.Loggable)")
    public void log(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Signature signature = joinPoint.getSignature();
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method method = null;
        for (Method m: methods) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        if (method != null) {
            Loggable loggable = method.getAnnotation(Loggable.class);
            if (loggable != null) {
                logger.info("[Annotation]: {}", loggable.toString());
                logger.info("[Annotation Value]: {}", loggable.value());
            }
        }
        logger.info("[{}]: called", methodName);
        logger.info("[Target]: {}",  joinPoint.getTarget().getClass());
    }
}
