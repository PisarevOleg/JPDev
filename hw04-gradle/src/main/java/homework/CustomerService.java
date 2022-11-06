package homework;


import java.util.*;

public class CustomerService {
    Comparator<Customer> compare = new Comparator<Customer>() {
        @Override public int compare(Customer e1, Customer e2) {
            return Long.compare(e1.getScores(),e2.getScores());
        }
    };
    private final NavigableMap<Customer, String> map = new TreeMap<>(compare);
    // так не интересно
    //private final NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparing(Customer::getScores));

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
