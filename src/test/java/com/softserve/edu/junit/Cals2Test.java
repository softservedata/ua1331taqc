package com.softserve.edu.junit;

import com.softserve.edu.Calc;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Cals2Test {
    //private Calc calc = new Calc();
    private Calc calc;

    @BeforeAll
    public void setup() {
        calc = new Calc();
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public void tear() {
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() {
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() {
        System.out.println("\t@AfterEach executed");
    }


    @Test
    public void checkAdd1() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 10.0005;
        actual = calc.add(5, 5);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkAdd1 executed");
    }

    @Test
    public void checkAdd2() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 9;
        actual = calc.add(5, 4);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkAdd2 executed");
    }

    @Test
    public void checkDiv1() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 4;
        actual = calc.div(20, 5);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkDiv1 executed");
    }

    @Test
    public void checkDiv2() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 2.5;
        actual = calc.div(20, 8);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkDiv2 executed");
    }
}
