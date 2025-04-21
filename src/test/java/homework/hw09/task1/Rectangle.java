package homework.hw09.task1;

public class Rectangle {
    private final double angle = 90.0;
    private double width;
    private double height;


    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea() {
        validateDimensions();
        return width * height;
    }

    public double calculatePerimeter() {
        validateDimensions();
        return 2 * (width + height);
    }

    public double getDiagonal() {
        validateDimensions();
        double diagonal = Math.sqrt(width * width + height * height);
        return Math.round(diagonal * 100.0) / 100.0;
    }

    private void validateDimensions() {
        if (width <= 0 || height <= 0) {
            throw new IllegalStateException("Both width and height must be positive to perform this calculation.");
        }
    }
}
