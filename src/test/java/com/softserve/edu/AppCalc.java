package com.softserve.edu;

import java.util.Scanner;

public class AppCalc {

    /*
    // Not Testability
    // Spagetti Code
    public static void main(String[] args) {
        System.out.println("Start");
        Scanner sc = new Scanner(System.in);
        //
        System.out.print("\ta = ");
        int a = sc.nextInt();
        System.out.print("\tb = ");
        int b = sc.nextInt();
        //
        int result = a + b;
        System.out.print("\tresult = " + result);
        sc.close();
    }
    */

    public static void main(String[] args) {
        System.out.println("Start");
        Calc calc = new Calc();
        //
        double resultAdd = calc.add(5, 5);
        double resultDiv = calc.div(20, 5);
        //
        System.out.println("\tresultAdd = " + resultAdd);
        System.out.println("\tresultDiv = " + resultDiv);
    }

}