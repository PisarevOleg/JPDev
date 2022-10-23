package homework;


import java.util.*;

public class CustomerService {
    private final NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        Map.Entry<Customer, String> smallestKey = map.firstEntry();
        return (smallestKey != null ? new AbstractMap.SimpleEntry<>(new Customer(smallestKey.getKey()), smallestKey.getValue()) : null);

        //return null; // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextItem = map.higherEntry(customer);
        return (nextItem != null ? new AbstractMap.SimpleEntry<>(new Customer(nextItem.getKey()), nextItem.getValue()) : null);

        //return null; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
