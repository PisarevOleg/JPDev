package com.example;

import homework.Customer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        customerAsKeyTest();
    }

    static void customerAsKeyTest() {
        //given
        final long customerId = 1L;
        Customer customer = new Customer(customerId, "Ivan", 233);
        Map<Customer, String> map = new HashMap<>();

        String expectedData = "data";
        map.put(customer, expectedData);

        //when
        long newScore = customer.getScores() + 10;
        String factData = map.get(new Customer(customerId, "IvanChangedName", newScore));
        System.out.println("factData = " + factData);
        //then
        if (factData.equals(expectedData)) {
            System.out.println("yes expectedData=" + expectedData);
        }

        //when
        long newScoreSecond = customer.getScores() + 20;
        customer.setScores(newScoreSecond);
        String factDataSecond = map.get(customer);

        //then
        System.out.println("factDataSecond = " + factDataSecond);
        if (factData.equals(expectedData)) {
            System.out.println("yes factDataSecond=" + factDataSecond);
        }
    }
}