package com.softserve.edu.pr09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
    @Test
    @DisplayName("Constructor should set width and height correctly")
    void testConstructor() {
        Rectangle rectangle = new Rectangle(4.0, 8.0);
        assertEquals(4.0, rectangle.getWidth());
        assertEquals(8.0, rectangle.getHeight());
    }

    @Test
    @DisplayName("Setters and getters should work correctly")
    void testSettersAndGetters() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(3.5);
        rectangle.setHeight(7.0);
        assertEquals(3.5, rectangle.getWidth());
        assertEquals(7.0, rectangle.getHeight());
    }

    @ParameterizedTest
    @DisplayName("Constructor should throw IllegalArgumentException for invalid width/height")
    @CsvSource({
            "0, 5",
            "-1, 5",
            "5, 0",
            "5, -1"
    })
    void testConstructorWithInvalidValues(double width, double height) {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(width, height));
    }

    @Test
    @DisplayName("Method setWidth should throw exception when width <= 0")
    void testSetWidthWithInvalidValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(0));
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-5));
    }

    @Test
    @DisplayName("Method setHeight should throw exception when height <= 0")
    void testSetHeightWithInvalidValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(0));
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5));
    }

    @Test
    @DisplayName("Method getAngle should always return 90 degrees")
    void testGetAngle() {
        Rectangle rectangle = new Rectangle();
        assertEquals(90, rectangle.getAngle());
    }

    @Test
    @DisplayName("Method checkArea calculates the area correctly")
    public void checkArea(){
        Rectangle rectangle = new Rectangle(5, 3);
        double actual;
        double expected;

        expected = 15;
        actual = rectangle.calculateArea();
        Assertions.assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Method checkPerimeter calculates the perimeter correctly")
    public void checkPerimeter(){
        Rectangle rectangle = new Rectangle(5, 3);
        double actual;
        double expected;

        expected = 16;
        actual = rectangle.calculatePerimeter();
        Assertions.assertEquals(expected, actual, 0.01);
    }

    @Test
    @DisplayName("Method checkDiagonal calculates the diagonal correctly")
    public void checkDiagonal(){
        Rectangle rectangle = new Rectangle(5, 3);
        double actual;
        double expected;

        expected = 5.83;
        actual = rectangle.calculateDiagonal();
        Assertions.assertEquals(expected, actual, 0.01);
    }
}

