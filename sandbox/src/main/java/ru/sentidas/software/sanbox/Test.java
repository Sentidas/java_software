package ru.sentidas.software.sanbox;

import java.util.Scanner;

public class Test {
  public static void main(String[] args) {

    Square s = new Square(5);

    System.out.println("площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(2, 3 );

    System.out.println("площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String some) {
    System.out.println("Привет " + some);

  }

}