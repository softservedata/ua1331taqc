package com.softserve.edu.junit;

import com.softserve.edu.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Calc3ParTest {

    private Calc calc;

    @BeforeAll
    public void setup() {
        calc = new Calc();
        System.out.println("@BeforeAll executed");
    }

    @ParameterizedTest(name = "{index} => arg0={0}, arg1={1}, expected={2}")
    @CsvSource({
            "5, 5, 10",
            "5, 4, 9"
    })
    public void checkAdd(double arg0, double arg1, double expected) {
        double actual = calc.add(arg0, arg1);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkAdd1 executed");
    }

    @ParameterizedTest(name = "{index} => arg0={0}, arg1={1}, expected={2}")
    @CsvSource({
            "20, 5, 4",
            "20, 8, 2.5"
    })
    public void checkDiv(double arg0, double arg1, double expected) {
        double actual = calc.div(arg0, arg1);
        Assertions.assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkDiv executed");
    }
}
