package com.example.AOPServices;

import org.aspectj.lang.annotation.Pointcut;

public class DeclarePointcuts {
    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicMethod() {
    }

//    @Pointcut(value = "anyPublicMethod() && @annotation(com.example.Annotations.CheckedMethod)")
    @Pointcut(value = "@annotation(com.example.Annotations.CheckedMethod)")
    public void annotationCatcher() {}
}
