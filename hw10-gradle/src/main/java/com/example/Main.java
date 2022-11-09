package com.example;

import com.example.AOPServices.MethodsRunner;
import com.example.Annotations.CheckedMethod;
import com.example.ProxyServices.ProxyModifier;
import com.example.ScoupeModifiedClasses.OneModifiedClass;
import com.example.ScoupeModifiedClasses.TwoModifiedClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Predicate<Method> checker = new Predicate<Method>() {
            @Override
            public boolean test(Method method) {
                return !Arrays.stream(method.getAnnotations()).filter(CheckedMethod.class::isInstance).toList().isEmpty();
            }
        };

        System.out.println("------------------------- Proxy >");
        proxyMain(checker);
        System.out.println("------------------------- Proxy <");

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        System.out.println("------------------------- Spring-AOP >");
        aopMain(context, checker);
        System.out.println("------------------------- Spring-AOP <");
    }

    public static void proxyMain(Predicate<Method> checker) {
        System.out.println("--- check proxy-method >");
        (new ProxyModifier()).tester(new OneModifiedClass(),checker);
        (new ProxyModifier()).tester(new TwoModifiedClass(),checker);
        System.out.println("--- check proxy-method <");

    }


    public static void aopMain(ConfigurableApplicationContext context, Predicate<Method> checker) {
        System.out.println("--- check aop-method >");
        context.getBean(MethodsRunner.class).tester(
                context.getBean(OneModifiedClass.class)
        );
        //context.getBean(MethodsRunner.class).tester(new TwoModifiedClass());
        System.out.println("--- check aop-method <");

    }

    @Bean
    MethodsRunner methodsRunner() {
        return new MethodsRunner();
    }

    @Bean
    OneModifiedClass oneModifiedClass() {
        return new OneModifiedClass();
    }
}