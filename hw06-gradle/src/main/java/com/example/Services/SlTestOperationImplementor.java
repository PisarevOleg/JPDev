package com.example.Services;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlTest;
import com.example.Exceptions.CheckedFooException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@Service
public class SlTestOperationImplementor {
    public List<Boolean> checkInstance(String checkNameInstance) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object checkItemInstance = ((Class.forName(checkNameInstance)).getConstructor()).newInstance();

        List<Method> beforeMethods = Arrays.stream(checkItemInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlBefore.class::isInstance).toList().isEmpty()
        ).toList();

        List<Method> testMethods = Arrays.stream(checkItemInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlTest.class::isInstance).toList().isEmpty()
        ).toList();

        List<Method> afterMethods = Arrays.stream(checkItemInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlAfter.class::isInstance).toList().isEmpty()
        ).toList();

        return testMethods.stream().map(t -> {
            boolean errorFlag = false;

            try {
                Class<?> clazz = Class.forName(checkNameInstance);
                Constructor<?> constructor = clazz.getConstructor();
                Object checkInstance= constructor.newInstance();

                try {
                    execBeforeMethods(checkInstance,beforeMethods);
                    execTestMethods(checkInstance,testMethods);

                } catch (Exception e) {
                    errorFlag=true;
                    System.out.println("an error occurred ... the test check broke and aborted");

                } finally {
                    try {
                        execAfterMethods(checkInstance, afterMethods);
                    } catch (Exception e) {
                        errorFlag = true;
                    }
                }
            } catch (Exception e) {
                errorFlag = true;
                System.out.println("an error occurred ...");
            }

            return errorFlag;
        }).toList();
    }

    private <T> void execBeforeMethods(T checkInstance, List<Method> beforeMethods) throws InvocationTargetException, IllegalAccessException {
        for(Method method: beforeMethods) {
            if(!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
            method.invoke(checkInstance);
        }
    }

    private <T> void execTestMethods(T checkInstance, List<Method> testMethods) throws InvocationTargetException, IllegalAccessException {
        for(Method method: testMethods) {
            if(!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
            method.invoke(checkInstance);
        }
    }

    private <T> void execAfterMethods(T checkInstance, List<Method> afterMethods) {
        boolean errorFlag = false;

        for(Method method: afterMethods) {
            try {
                if (!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
                method.invoke(checkInstance);
            } catch (Exception e) {
                errorFlag=true;
                System.out.println("an exception occurred ... ignore it and still working for @After methods ...");
            }
        }
        if(errorFlag) new CheckedFooException("there is error of one \"after\" method ...");
    }
}