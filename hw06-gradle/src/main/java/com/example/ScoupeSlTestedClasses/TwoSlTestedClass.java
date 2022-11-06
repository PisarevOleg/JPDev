package com.example.ScoupeSlTestedClasses;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlClassMark;
import com.example.Annotation.SlTest;
import com.example.Exceptions.UncheckedFooException;
import com.example.SlClass;

import java.lang.invoke.MethodHandles;

@SlClassMark(TestClass=true)
public class TwoSlTestedClass extends SlClass {
    @SlBefore
    void doFirstPreparation()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlTest
    private void doTestExecution()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlAfter
    private void doFinalCompletion1()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());

        System.out.println("now throw CheckedFooException ...");
        throw new UncheckedFooException("there is checked exception message ...");
    }

    @SlAfter
    private void doFinalCompletion2()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
}
