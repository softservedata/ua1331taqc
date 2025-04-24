package com.softserve.homeWork.task09;

public class RectangleApp {
    private double width;
    private double height;
    private final double angle = 90.0;


    public RectangleApp() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public RectangleApp(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width and height must be positive numbers.");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive numbers.");
        }
        this.height = height;
    }

    public double getAngle(double angle) {
        return angle;
    }

    private void validateDimensions() {
        if (width <= 0 || height <= 0) {
            throw new IllegalStateException("Width and height must be positive numbers.");
        }
    }

    public double calculateArea() {
        validateDimensions();
        return width * height;
    }

    public double calculatePerimeter(){
        validateDimensions();
        return 2 * (width + height);
    }

    public double getDiagonal() {
        validateDimensions();
        return Math.sqrt(width * width + height * height);
    }

}
