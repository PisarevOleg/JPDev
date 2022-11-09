package com.example.ScoupeModifiedClasses;

import com.example.Annotations.CheckedMethod;
import com.example.Annotations.Log;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class TwoModifiedClass implements TwoTestLoggingInterface, OneTestLoggingInterface {
    @Log
    @Override
    public void twoCalculation(int param1) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @CheckedMethod
    @Override
    public void twoCalculation(int param1, String param2) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @CheckedMethod
    @Log
    @Override
    public void twoCalculation(int param1, int param2, int param3) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @CheckedMethod
    @Log
    @Override
    public void twoCalculation(int param1, int param2, int param3, String param4) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @Override
    public void oneCalculation(int param1) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @Override
    public void oneCalculation(int param1, int param2) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @CheckedMethod
    @Log
    @Override
    public void oneCalculation(int param1, int param2, int param3) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }

    @Override
    public void oneCalculation(int param1, int param2, int param3, int param4) {
        System.out.println("Message from:  className(" + MethodHandles.lookup().lookupClass().getCanonicalName() +
                "), methodName(" + new Object() {
        }.getClass().getEnclosingMethod().getName() +
                "), arguments(" + Arrays.stream(new Object() {}.getClass().getEnclosingMethod().getParameterTypes()).toList() + ")");
    }
}
