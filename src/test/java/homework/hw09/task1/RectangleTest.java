package homework.hw09.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {
    private final Rectangle rectangle = new Rectangle();

    @Test
    @DisplayName("Default Constructor should initialize width and height to 0, angle to 90")
    public void testDefaultConstructor() {
        assertEquals(rectangle.getWidth(), 1.0);
        assertEquals(rectangle.getHeight(), 1.0);
        assertEquals(rectangle.getAngle(), 90);
    }

    @Test
    @DisplayName("Positive test calculatePerimeter()")
    public void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(5,4);
        assertEquals(rectangle.calculatePerimeter(), 18.0);
    }

    @Test
    @DisplayName("Positive test calculateArea()")
    public void testCalculateArea(){
        Rectangle rectangle = new Rectangle(3,5);
        assertEquals(rectangle.calculateArea(), 15.0);
    }

    @Test
    @DisplayName("Positive test getDiagonal()")
    public void testGetDiagonal(){
        Rectangle rectangle = new Rectangle(2,6);
        assertEquals(rectangle.getDiagonal(), 6.32);
    }

    @ParameterizedTest
    @DisplayName("Positive test calculateArea() should return correct area for different width and height values")
    @CsvSource({
            "5.0, 10.0, 50.0",
            "7.0, 3.0, 21.0",
            "1.0, 10.0, 10.0",
            "6.0, 6.0, 36.0"
    })
    public void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculateArea());
    }

    @Test
    @DisplayName("Negative test, Should throw IllegalArgumentException when width is negative ")
    public void testNegativeWidth() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(-1.0, 5.0);
        });
        assertEquals("Width must be positive", thrown.getMessage());
    }

    @Test
    @DisplayName("Negative test, Should throw IllegalArgumentException when setting a negative width")
    public void testSetNegativeWidth() {
        Rectangle rectangle = new Rectangle(5.0, 5.0);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            rectangle.setWidth(-2.0);
        });
        assertEquals("Width must be positive", thrown.getMessage());
    }


    @Test
    public void testSetNegativeHeight() {
        Rectangle rectangle = new Rectangle(5.0, 5.0);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            rectangle.setHeight(-3.0);
        });
        assertEquals("Height must be positive", thrown.getMessage());
    }
}