package com.softserve.homeWork.task09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class RandomArray {
    Random random = new Random();
    public List<Integer> arrayList = new ArrayList<>();

    public List<Integer> consoleWritten() {
        while (arrayList.size() < 20) {
            arrayList.add(random.nextInt(100) + 1);
        }
        return arrayList;
    }

    public static List<Integer> findMaxMinValue(List<Integer> values) {
        List<Integer> sortList = new ArrayList<>();
        if (!values.isEmpty()) {
            sortList.add(Collections.max(values));
            sortList.add(Collections.min(values));
        }
        return sortList;
    }

    public static int averageValue(List<Integer> values) {
        int sum = 0;
        for (int num : values) {
            sum += num;
        }
        return sum / values.size();
    }

    public static List<Integer> evenNumbers(List<Integer> values) {
        List<Integer> even = new ArrayList<>();
        for (int num : values) {
            if (num % 2 == 0) {
                even.add(num);
            }
        }
        return even;
    }


    public static boolean number50 (List<Integer> values) {
        for (int num : values) {
            if (num == 50 ) {
                System.out.println(num);
                return true;
            }
        }
        return false;
    }

    public static List<Integer> sortList(List<Integer> values){
        Collections.sort(values);
        return values;
    }

    public static void main(String[] args) {
        RandomArray randomArray = new RandomArray();
        System.out.println(randomArray.consoleWritten());
        System.out.println("\tFind min and max");
        System.out.println(findMaxMinValue(randomArray.arrayList));
        System.out.println("");
        System.out.println(averageValue(randomArray.arrayList));
        System.out.println("");
        System.out.println(evenNumbers(randomArray.arrayList));
        System.out.println("50 Find");
        randomArray.number50(randomArray.arrayList);
        System.out.println("");
        System.out.println(randomArray.sortList(randomArray.arrayList));


    }
}


