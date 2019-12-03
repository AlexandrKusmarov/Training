package main;

public class QuadraticEquation {
    public static void main(String[] args) {
        resolveAcq(1,-2,-3);
        System.out.println();
        resolveAcq(-1,-2,15);
        System.out.println();
        resolveAcq(1,12,36);
        System.out.println();
        resolveAcq(1,0,0);
    }

    private static void resolveAcq(float a, float b, float c) {
        float x1 = 0;
        float x2 = 0;
        float discrim = discriminant(a, b, c);

        if (discrim < 0) {
            System.out.println("Уравнение не имеет корней.");
        } else if (discrim == 0) {
            x1 = (-b + (float) Math.sqrt(discrim)) / 2 * a;
            System.out.println("Уравнение имеет 1 корень: " + x1);
        } else {
            x1 = (-b + (float) Math.sqrt(discrim)) / 2 * a;
            x2 = (-b - (float) Math.sqrt(discrim)) / 2 * a;
            System.out.printf("Уравнение имеет 2 корня: x1= %s x2= %s", x1, x2);
        }
    }

    private static float discriminant(float a, float b, float c) {
        return b * b - 4 * a * c;
    }

}
