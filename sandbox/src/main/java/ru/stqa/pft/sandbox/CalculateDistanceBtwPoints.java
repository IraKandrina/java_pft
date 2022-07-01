package ru.stqa.pft.sandbox;

public class CalculateDistanceBtwPoints {

    public static void main(String[] args) {
        //Point p1 = new Point(-5, 0);
        //Point p2 = new Point(10, -3);

        Point p1 = new Point(10, 10);
        Point p2 = new Point(13, 14);

        System.out.println("Расстояние между " + p1.showCoordinates() + " и  " + p2.showCoordinates() + " = " + p1.distance(p2));
    }

}
