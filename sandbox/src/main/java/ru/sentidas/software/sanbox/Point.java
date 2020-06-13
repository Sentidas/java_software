package ru.sentidas.software.sanbox;

public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  static double distance(Point p1, Point p2) {
    double s = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    return s;
  }

}