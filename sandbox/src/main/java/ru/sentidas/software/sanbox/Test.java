
package ru.sentidas.software.sanbox;

public class Test {

  public static void main(String[] args) {

    Point p1 = new Point(5, 3);
    Point p2 = new Point(1, 4);
    double s = distance(p1, p2);
    System.out.println("Расстояние между двумя точками равно " + s);

  }

  public static double distance(Point p1, Point p2) {
    double s = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    return s;
  }
}