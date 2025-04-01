package com.softserve.edu02junit;

import com.softserve.edu.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcTest {

    @Test
    public void checkAdd1() {
        Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 10.0005;
        actual = calc.add(5, 5);
        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void checkAdd2() {
        Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 9;
        actual = calc.add(5, 4);
        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void checkDiv1() {
        Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 4;
        actual = calc.div(20, 5);
        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void checkDiv2() {
        Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 2.5;
        actual = calc.div(20, 8);
        Assertions.assertEquals(expected, actual, 0.001);
    }
}
