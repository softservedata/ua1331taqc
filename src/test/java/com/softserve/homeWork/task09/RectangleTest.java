package com.softserve.homeWork.task09;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class RectangleTest {

    //private RectangleApp rectangleApp;
//
//    @BeforeAll
//    public void setup() {
//        rectangleApp = new RectangleApp();
//        System.out.println("@BeforeAll executed");
//    }
    private final RectangleApp rectangle = new RectangleApp();

//    @Test
//    @DisplayName("calculateArea() should return correct area for valid width and height")
//    public void calculateAreaTest() {
//        RectangleApp rectangleApp = new RectangleApp(5, 5);
//
//        assertEquals(25, rectangleApp.calculateArea(), 0.001);
//        System.out.println("\t\t@checkAdd1 executed");
//    }

    @ParameterizedTest
    @DisplayName("calculateArea() should return correct area for different width and height values")
    @CsvSource({
            "5.0, 10.0, 50.0",
            "7.0, 3.0, 21.0",
            "1.0, 10.0, 10.0",
            "6.0, 6.0, 36.0"
    })
    public void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        RectangleApp rectangle = new RectangleApp(width, height);
        assertEquals(expectedArea, rectangle.calculateArea());
    }

    @Test
    public void calculatePerimeterTest() {
        RectangleApp rectangleApp = new RectangleApp(5, 5);

        double actual;
        double expected;

        expected = 20.0005;
        actual = rectangleApp.calculatePerimeter();
        assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkAdd1 executed");
    }

    @Test
    public void getDiagonalTest() {
        RectangleApp rectangleApp = new RectangleApp(3, 4);

        double actual;
        double expected;

        expected = 5;
        actual = rectangleApp.getDiagonal();
        assertEquals(expected, actual, 0.001);
        System.out.println("\t\t@checkAdd1 executed");
    }

    @Test
    void testInvalidDimensions() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RectangleApp rectangle = new RectangleApp(-5, 10);
        });
        System.out.println("Message = " + thrown.getMessage());
        assertEquals("Width and height must be positive numbers.", thrown.getMessage());
    }

    @Test
    @DisplayName("initialize width and height to provided values")
    public void testParameterizedConstructor() {
        RectangleApp rectangleApp = new RectangleApp(5.0, 10.0);
        assertEquals(5.0, rectangleApp.getWidth());
        assertEquals(10.0, rectangleApp.getHeight());
    }
}
