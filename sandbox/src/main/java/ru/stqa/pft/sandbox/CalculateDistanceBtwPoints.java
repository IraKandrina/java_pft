package ru.stqa.pft.sandbox;

public class CalculateDistanceBtwPoints {

    public static void main(String[] args) {
        Point p1 = new Point(-5, 0);
        Point p2 = new Point(10, -3);

        System.out.println("Расстояние между " + p1.showCoordinates() + " и  " + p2.showCoordinates() + " = " + distance(p1,p2));

        //System.out.println("Расстояние между " + p1.showCoordinates() + " и  " + p2.showCoordinates() + " = " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
