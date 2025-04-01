package com.softserve.edu02junit;

import com.softserve.edu.ArrGenerate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArrTest {

    private ArrGenerate arrGenerate;

    @BeforeAll
    public void setup() {
        arrGenerate = new ArrGenerate();
        System.out.println("@BeforeAll executed");
    }

    @Test
    public void checkArr() {
        int[] expected = {1, 3, 5, 7, 9};
        int[] actual = arrGenerate.getArray();
        Assertions.assertArrayEquals(expected, actual);
        System.out.println("\t\t@checkArr executed");
    }

    public static Object[][] numbers() {
        return new Object[][]{
                {new int[]{1, 2, 3, 4, 5}, 3},
                {new int[]{5, 4, 3, 2, 1}, 4},
                {new int[]{1, 2, 3, 4, 10}, 10}
        };
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void testThree(int[] arr, int num) {
        System.out.println("\t\t@Test testThree(), num = " + num);
        boolean isExist = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                isExist = true;
                break;
            }
        }
        Assertions.assertTrue(isExist, "Array should contain the number");
    }
}
