package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Double.NaN;

public class CalculateDistanceBtwPointsTests {

    @Test
    public void distancePositiveValuesTest(){

        Point p1 = new Point(10, 10);
        Point p2 = new Point(13, 14);

        Assert.assertEquals(p1.distance(p2),5.0);
    }

    @Test
    public void distanceZeroValuesTest(){

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);

        Assert.assertEquals(p1.distance(p2),0.0);
    }

    @Test
    public void distanceNegativeValuesTest(){

        Point p1 = new Point(-10, -10);
        Point p2 = new Point(-13, -14);

        Assert.assertEquals(p1.distance(p2),5.0);
    }

    @Test
    public void distanceNaNValueTest(){

        Point p1 = new Point(2.1%0, -10);
        Point p2 = new Point(-13, -14);

        Assert.assertEquals(p1.distance(p2),NaN);

    }

}
