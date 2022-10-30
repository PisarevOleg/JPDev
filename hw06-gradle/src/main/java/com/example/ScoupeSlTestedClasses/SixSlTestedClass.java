package com.example.ScoupeSlTestedClasses;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlClassMark;
import com.example.Annotation.SlTest;
import com.example.Exceptions.UncheckedFooException;
import com.example.SlClass;

import java.lang.invoke.MethodHandles;

@SlClassMark(TestClass=true)
public class SixSlTestedClass extends SlClass {
    @SlBefore
    private void doFirstPreparation()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());

        System.out.println("now throw UncheckedFooException ...");
        throw new UncheckedFooException("there is unchecked exception message ...");
    }

    @SlTest
    void doTestExecution()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlAfter
    void doFinalCompletion1()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlAfter
    void doFinalCompletion2()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
}
