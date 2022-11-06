package com.example.ScoupeSlTestedClasses;

import com.example.Annotation.SlAfter;
import com.example.Annotation.SlBefore;
import com.example.Annotation.SlClassMark;
import com.example.SlClass;

import java.lang.invoke.MethodHandles;

@SlClassMark(TestClass=true)
public class FourSlTestedClass extends SlClass {
    @SlBefore
    void doFirstPreparation()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }


    void doTestExecution()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @SlAfter
    void doFinalCompletion()
    {
        System.out.println("class name: " + MethodHandles.lookup().lookupClass().getCanonicalName() +
                ", method name: " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
}
