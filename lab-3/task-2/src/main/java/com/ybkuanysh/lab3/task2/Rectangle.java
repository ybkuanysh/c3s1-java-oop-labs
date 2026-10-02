package com.ybkuanysh.lab3.task2;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
        this(1, 1);
    }

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Стороны должны быть больше 0");
        }
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle other) {
        this(other.width, other.height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public Rectangle scale(double factor) {
        return new Rectangle(width * factor, height * factor);
    }

    public int compareTo(Rectangle other) {
        return Double.compare(area(), other.area());
    }

    @Override
    public String toString() {
        return String.format("%.1f x %.1f", width, height);
    }
}
