package main.java;

public class QuadraticEquationImpl implements QuadraticEquation {
    @Override
    public void resolveAcq(float a, float b, float c) {
        float x1;
        float x2;
        float discriminant = find(a, b, c);

        if (discriminant < 0) {
            System.out.println("The equation doesn't has roots.");
        } else if (discriminant == 0) {
            x1 = (-b + (float) Math.sqrt(discriminant)) / 2 * a;
            System.out.println("The equation has one root: " + x1);
        } else {
            x1 = (-b + (float) Math.sqrt(discriminant)) / 2 * a;
            x2 = (-b - (float) Math.sqrt(discriminant)) / 2 * a;
            System.out.printf("The equation has two roots: x1= %s, x2= %s", x1, x2);
        }
    }

    private static float find(float a, float b, float c) {
        return b * b - 4 * a * c;
    }
}
