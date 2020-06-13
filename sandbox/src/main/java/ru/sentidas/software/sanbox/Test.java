
package ru.sentidas.software.sanbox;

public class Test {

  public static void main(String[] args) {

    Point p1 = new Point(5, 3);
    Point p2 = new Point(1, 4);

    System.out.println("Расстояние между двумя точками c координатами (" + p1.x + ", " + p1.y + ") " +
            " и (" + p2.x + ", " + p2.y + ") равно " + p1.distance(p1, p2));
  }
}

