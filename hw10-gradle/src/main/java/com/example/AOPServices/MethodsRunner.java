package com.example.AOPServices;

import com.example.Annotations.CheckedMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MethodsRunner {

    public <T> List<Boolean> tester(T checkedInstanceClass) {
        List<Method> checkMethods = Arrays.stream((org.springframework.aop.support.AopUtils.getTargetClass(checkedInstanceClass)).getDeclaredMethods()).filter(
                m -> m.isAnnotationPresent(CheckedMethod.class)
        ).toList();

        AtomicInteger index = new AtomicInteger();
        return checkMethods.stream().map(method -> {
            try {
                method.invoke(checkedInstanceClass, Stream.of(
                        method.getParameterTypes()
                ).map(
                        t -> {
                            // УБОЖЕСТВО!
                            // до сих пор нет нормального каста одним методом между всеми базовыми типами и примитивами
                            // оставил для себя: var type = MethodType.methodType(t).wrap().returnType();
                            if (t == Integer.TYPE) return index.getAndIncrement();
                            return "" + index.getAndIncrement();
                        }
                ).toArray());
                return true;
            } catch (Exception e) {
                return false;
            }
        }).toList();
    }
}
