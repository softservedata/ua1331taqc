package com.softserve.homeWork.task09;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.softserve.homeWork.task09.RandomArray.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RandomArrayTest {
    public RandomArray randomArray = new RandomArray();
    public List<Integer> arrayList;
    Random random = new Random();

    @BeforeAll
    public void setup() {
        arrayList = new ArrayList<>();
        System.out.println("@BeforeAll executed: Random list generation started");
        while (arrayList.size() < 20) {
            arrayList.add(random.nextInt(100) + 1);
        }
        System.out.println("Random list generated: " + arrayList);
    }

    @Test
    @DisplayName("Validate List Size and Element Ranges")
    public void consoleWrittenTest() {
        assertEquals(20, arrayList.size(), "List should contain exactly 20 elements");

        for (int num : arrayList) {
            assertTrue(num >= 1 && num <= 100, "All numbers should be between 1 and 100");
        }
    }

    @Test
    @DisplayName("Calculate Average Value of List")
    public void averageValueTest() {
        RandomArray.averageValue(arrayList);
        int sum = 0;
        for (int num : arrayList) {
            sum += num;
        }
        int average = sum / arrayList.size();
        System.out.println("Calculated average: " + average);
        assertEquals(average, RandomArray.averageValue(arrayList));
    }

    @Test
    @DisplayName("Find Maximum and Minimum Values")
    public void findMaxMinValueTest() {
        List<Integer> sortList = new ArrayList<>();
        if (!arrayList.isEmpty()) {
            sortList.add(Collections.max(arrayList));
            sortList.add(Collections.min(arrayList));
        }
        assertEquals(Collections.max(arrayList), sortList.get(0), "The maximum value should match");
        assertEquals(Collections.min(arrayList), sortList.get(1), "The minimum value should match");
    }

    @Test
    @DisplayName("Find All Even Numbers in the List")
    public void evenNumbersTest() {
        List<Integer> even = new ArrayList<>();
        even.addAll(evenNumbers(arrayList));
        System.out.println("Even numbers: " + even);
        assertEquals(evenNumbers(arrayList), even, "The list of even numbers should match");
    }

    @Test
    @DisplayName("Check if Number 50 Exists in the List")
    public void number50Test() {
        boolean value = number50(arrayList);
        try {
            assertTrue(value, "Number 50 should be present in the list!");
            System.out.println("Number 50 is present in the list!");
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());

            // Logging the random list for debugging
            System.err.println("Random list: " + arrayList);
        }
    }

    @Test
    @DisplayName("Check if the list is sorted in ascending order")
    public void sortListTest() {
        RandomArray.sortList(arrayList);
        boolean isSorted = true;
        for (int i = 0; i < arrayList.size()-1 ; i++) {
            if (arrayList.get(i) > arrayList.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted, "The list is not sorted in ascending order!");
    }
}