package homework.hw09.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberAnalyzer {
    private ArrayList<Integer> numbers;

    public NumberAnalyzer() {
        numbers = new ArrayList<>();
    }

    // Fill the collection with 20 random numbers between 1 and 100
    public void fillWithRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            numbers.add(random.nextInt(100) + 1);
        }
    }

    public void printCollection() {
        System.out.println("Collection: " + numbers);
    }

    public void findMax() {
        if (numbers.isEmpty()) {
            System.out.println("No numbers found");
            return;
        }
        int min = Collections.min(numbers);
        int max = Collections.max(numbers);
        System.out.println("Max: " + max + " Min: " + min);
    }

    public double calculateAverage() {
        if (numbers.isEmpty()) {
            System.out.println("No numbers found");
            return 0.0;
        }
        double sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = sum / numbers.size();
        System.out.println("Average number: " + average);
        return average;
    }

    //Remove even numbers and print updated collection
    public void removeEvenNumbers() {
        numbers.removeIf(num -> num % 2 == 0);
        System.out.println("Collection after removing even numbers: " + numbers);
    }

    //Check if the collection contains a given number
    public boolean containsNumber(int number) {
        boolean contains = numbers.contains(number);
        System.out.println("Does the collection contain " + number + "? " + contains);
        return contains;
    }

    public void sortCollection() {
        Collections.sort(numbers);
        System.out.println("Sorted collection: " + numbers);
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}
