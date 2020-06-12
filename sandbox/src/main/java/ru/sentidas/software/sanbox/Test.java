package ru.sentidas.software.sanbox;

public class Test {
  public static void main(String[] args) {



  double l = 5;
    System.out.println("площадь квадрата со стороной " + l + " = " + area(l));

    double a = 2;
    double b = 3;
    System.out.println("площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangle(a,b));


  }



  public static void hello (String some) {
    System.out.println("ПРивет " + some);

  }
  public static double area (double l) {
    return l* l;
  }

  public static double rectangle (double a, double b) {
    return a * b;
  }

}