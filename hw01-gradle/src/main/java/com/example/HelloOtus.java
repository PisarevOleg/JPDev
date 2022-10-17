package com.example;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

public class HelloOtus {
    public void sayHello(){
        System.out.println("Hi. Here is sayHello method runned ...");

        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('b', 'c', 'd');

        Set<Character> union = Sets.union(first, second);
        System.out.println("union=" + union);
    }
}
