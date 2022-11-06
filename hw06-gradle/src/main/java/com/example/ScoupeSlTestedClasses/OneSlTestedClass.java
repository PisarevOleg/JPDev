package com.example.ScoupeSlTestedClasses;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlClassMark;
import com.example.Annotation.SlTest;
import com.example.Exceptions.CheckedFooException;
import com.example.SlClass;

import java.lang.invoke.MethodHandles;

@SlClassMark(TestClass=true)
public class OneSlTestedClass extends SlClass {
    @SlBefore
    void doFirstPreparation()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlTest
    public void doTestExecution() throws CheckedFooException {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());

        System.out.println("now throw CheckedFooException ...");
        throw new CheckedFooException("there is checked exception message ...");
    }

    @SlAfter
    void doFinalCompletion()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
}
