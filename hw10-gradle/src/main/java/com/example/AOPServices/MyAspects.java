package com.example.AOPServices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspects {
    @Around(value = "com.example.AOPServices.DeclarePointcuts.annotationCatcher()")
    public Object logEmplemeter(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("AOP-method / proxied logged message form methodSignature = " + methodSignature.toString());

        return joinPoint.proceed();
    }

}
