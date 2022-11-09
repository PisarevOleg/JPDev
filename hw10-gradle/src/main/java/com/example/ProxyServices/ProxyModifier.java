package com.example.ProxyServices;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ProxyModifier {
    // в классе несколько ответственностей, но ... т.к. это учебный проект - не стал разделять на сущности
    public <T> List<Boolean> tester(T checkedInstanceClass, Predicate<Method> checker) {
        ProxyImplementor implementor = new ProxyImplementor();
        Object proxiedInstance = implementor.createClass(checkedInstanceClass);

        List<Method> checkMethods = Arrays.stream(checkedInstanceClass.getClass().getDeclaredMethods()).filter(checker::test).toList();

        AtomicInteger index = new AtomicInteger();
        return checkMethods.stream().map(method -> {
            if (!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
            try {
                proxiedInstance.getClass().getMethod(method.getName(), method.getParameterTypes()).
                        invoke(proxiedInstance,Stream.of(
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
