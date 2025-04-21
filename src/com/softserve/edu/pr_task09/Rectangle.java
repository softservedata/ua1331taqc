package com.softserve.edu.pr_task09;

public class Rectangle {
    private double width;
    private double height;
    public static final double angle = 90;

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
            throw new IllegalArgumentException("Width must be greater than 0");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculatePerimeter(){
        return (width + height) * 2;
    }

    public double calculateDiagonal(){
        return Math.sqrt(width * width + height * height);
    }
}