package com.example.Services;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlTest;
import com.example.Exceptions.CheckedFooException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@Service
public class SlTestOperationImplementor {
    public <T> Boolean CheckInstance(T checkInstance) {
        boolean errorFlag = false;
        List<Method> beforeMethods = Arrays.stream(checkInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlBefore.class::isInstance).toList().isEmpty()
        ).toList();

        List<Method> testMethods = Arrays.stream(checkInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlTest.class::isInstance).toList().isEmpty()
        ).toList();

        List<Method> afterMethods = Arrays.stream(checkInstance.getClass().getDeclaredMethods()).filter(
                m -> !Arrays.stream(m.getAnnotations()).filter(SlAfter.class::isInstance).toList().isEmpty()
        ).toList();

        try {
            ExecBeforeMethods(checkInstance,beforeMethods);
            ExecTestMethods(checkInstance,testMethods);

        } catch (Exception e) {
            errorFlag=true;
            System.out.println("an error occurred ... the test check broke and aborted");

        } finally {
            try {
                ExecAfterMethods(checkInstance, afterMethods);
            } catch (Exception e) {
                errorFlag = true;
            }
        }

        return !errorFlag;
    }

    private <T> void ExecBeforeMethods(T checkInstance,List<Method> beforeMethods) throws InvocationTargetException, IllegalAccessException {
        for(Method method: beforeMethods) {
            if(!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
            method.invoke(checkInstance);
        }
    }

    private <T> void ExecTestMethods(T checkInstance,List<Method> testMethods) throws InvocationTargetException, IllegalAccessException {
        for(Method method: testMethods) {
            if(!Modifier.isPublic(method.getModifiers())) method.setAccessible(true);
            method.invoke(checkInstance);
        }
    }

    private <T> void ExecAfterMethods(T checkInstance,List<Method> afterMethods) {
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
