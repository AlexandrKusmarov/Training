package main.java;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation();

        equation.resolveAcq(1, -2, -3);
        System.out.println();
        equation.resolveAcq(-1, -2, 15);
        System.out.println();
        equation.resolveAcq(1, 12, 36);
        System.out.println();
        equation.resolveAcq(1, 0, 0);
    }
}
