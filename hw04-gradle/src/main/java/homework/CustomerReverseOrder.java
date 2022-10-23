package homework;


import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class CustomerReverseOrder {
    Queue<Customer> queue= Collections.asLifoQueue(new ArrayDeque<>());

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        queue.add(customer);
    }

    public Customer take() {
        return queue.poll();

        //return null; // это "заглушка, чтобы скомилировать"
    }
}
