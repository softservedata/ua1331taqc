package com.softserve.edu02junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

public class ParJUnit5Test {
    // Field data provider
    private static List<String> cities = Arrays.asList("Madrid", "Rome", "Paris", "London");

    private boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return str.isBlank();
    }

    @ParameterizedTest
    @FieldSource("cities")
    void isBlank_ShouldReturnFalseWhenTheArgHasAtLEastOneCharacter(String arg) {
        System.out.println("arg = " + arg);
        Assertions.assertFalse(isBlank(arg));
    }

    public enum Size {
        XXS, XS, S, M, L, XL, XXL, XXXL;
    }

    @ParameterizedTest
    @EnumSource(value = Size.class, names = {"L", "XL", "XXL", "XXXL"}) // Includes
    //@EnumSource(value = Size.class)
    void testEnumInclude(Size size) {
        System.out.println("size.name() = " + size.name());
        Assertions.assertTrue(EnumSet.allOf(Size.class).contains(size));
    }

    @ParameterizedTest
    @EnumSource(value = Size.class, mode = EXCLUDE, names = {"XXS", "XS", "S"})
    void testEnumExclude(Size size) {
        System.out.println("size.name() = " + size.name());
        EnumSet<Size> excludeSmallSize = EnumSet.range(Size.M, Size.XXXL);
        Assertions.assertTrue(excludeSmallSize.contains(size));
    }

    @DisplayName("Should pass a non-null message to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        System.out.println("Message = " + message);
        Assertions.assertNotNull(message);
    }

    @ParameterizedTest
    //@NullSource
    //@EmptySource
    @NullAndEmptySource
    void testProcessInvalidPhones(String phone) {
        System.out.println("phone = " + phone);
        if (phone != null) {
            System.out.println("\tphone.length() = " + phone.length());
        }
        //Assertions.assertFalse(phone);
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5"
    })
    void sum(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void shouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        Assertions.assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void shouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        Assertions.assertEquals(expected, actualValue);
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvFileSource(resources = "/test-data.csv", delimiter = ';', numLinesToSkip = 1)
    void sum2(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
        String actualValue = input.toUpperCase();
        Assertions.assertEquals(expected, actualValue);
    }

    public static Object[][] sum5Provider() {
        System.out.println("\tsum5Provider done");
        return new Object[][]{
                {1, 2, 3},
                {5, 4, 9},
                {11, 2, 13}
        };
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sum5Provider")
    void sum5(int a, int b, int sum) {
        System.out.println("a = " + a + "  b = " + "  sum = " + sum);
        Assertions.assertEquals(sum, a + b);
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
        for (int i = 0; i<arr.length; i++) {
            if (arr[i] == num) {
                isExist = true;
                break;
            }
        }
        Assertions.assertTrue(isExist, "Array should contain the number");
    }

    public static Object[][] passwordProvider() {
        String passwordOS = System.getenv("JAVA_HOME");
        String passwordIDE = System.getenv().get("MY_IDE");
        String passwordMaven = System.getProperty("surefire.application.password");
        return new Object[][]{
                { passwordOS },
                { passwordIDE },
                { passwordMaven }
        };
    }

    @ParameterizedTest
    @MethodSource("passwordProvider")
    void checkPassword(String password) {
        System.out.println("password = " + password);
        Assertions.assertNotNull(password);
    }

    private static Stream<Arguments> sumProvider() {
        System.out.println("\tArguments sumProvider done");
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5),
                Arguments.of(12, 13, 25)
        );
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sumProvider")
    void sum3(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    private static Stream<String> shouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }

    @ParameterizedTest
    @MethodSource
    void shouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        boolean result = input == null ? true : input.isBlank();
        //Assertions.assertTrue(input.isBlank());
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu02junit.StringParams#blankStrings")
    void shouldReturnTrueForNullOrBlankStringsExternalSource(String input) {
        System.out.println("input = " + input);
        boolean result = input == null ? true : input.isBlank();
        //Assertions.assertTrue(input.isBlank());
        Assertions.assertTrue(result);
    }

    @Test
    void testExpectedException() {
        RuntimeException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
            //Code under test
            int i = 0;
            i = 10 / (i + 0);
        });
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertEquals("/ by zero", thrown.getMessage());
    }
}
