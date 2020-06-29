package ru.sentidas.software.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sentidas.software.sanbox.Point;

public class PointsTest {

  @Test
  public void testPoint() {
    Point p1 = new Point(5, 4);
    Point p2 = new Point(1, 0);
    Assert.assertEquals(p1.distance(p2), 5.656854249492381);
  }
  @Test
  public void testPoint1() {
    Point p1 = new Point(-2, 15);
    Point p2 = new Point(10, 0);
    Assert.assertEquals(p1.distance(p2), 19.209372712298546);
  }
  @Test
  public void testPoint2() {
    Point p1 = new Point(-690, 3);
    Point p2 = new Point(98, 2);
    Assert.assertEquals(p1.distance(p2), 788.000634517511);
  }

}
