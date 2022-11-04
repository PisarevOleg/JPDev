package ru.calculator;

public class Summator {
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;
    //private final ArrayList<Data> listValues = new ArrayList<>();
    private int dataCounter=0;

    //!!! сигнатуру метода менять нельзя
    //public void calc(Data data) {
    public void calc(int data) {
        dataCounter++;
        //listValues.add(data);
        //if (listValues.size() % 6_600_000 == 0) {
        //    listValues.clear();
        //}
        if (dataCounter % 6_600_000 == 0) {
            dataCounter=0;
        }
        sum += data;

        sumLastThreeValues = data + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = data;

        for (var idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (data + 1) - sum);
            someValue = Math.abs(someValue) + dataCounter;
        }
    }

    public Integer getSum() {
        return sum;
    }

    public Integer getPrevValue() {
        return prevValue;
    }

    public Integer getPrevPrevValue() {
        return prevPrevValue;
    }

    public Integer getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public Integer getSomeValue() {
        return someValue;
    }
}
