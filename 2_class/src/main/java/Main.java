package main.java;

import main.java.entity.Discriminant;
import main.java.entity.EquationRoot;

public class Main {
    private Discriminant discriminant = new Discriminant();
    private EquationRoot equationRoot = new EquationRoot();

    public static void main(String[] args) {
        Main main = new Main();

        main.resolveAcq(1, -2, -3);
        System.out.println();
        main.resolveAcq(-1, -2, 15);
        System.out.println();
        main.resolveAcq(1, 12, 36);
        System.out.println();
        main.resolveAcq(1, 0, 0);
    }

    private void resolveAcq(float a, float b, float c) {
        discriminant.setValue(findDiscriminant(a, b, c));
        float discrim = 0;

        if (discriminant != null) {
            discrim = discriminant.getValue();
        } else {
            throw new NullPointerException("Discriminant can't be null!");
        }

        if (discrim < 0) {
            System.out.println("The equation doesn't has roots.");
        } else if (discrim == 0) {
            equationRoot.setRoot1((-b + (float) Math.sqrt(discrim)) / 2 * a);
            System.out.println("The equation has one root: " + equationRoot.getRoot1());
        } else {
            equationRoot.setRoot1((-b + (float) Math.sqrt(discrim)) / 2 * a);
            equationRoot.setRoot2((-b - (float) Math.sqrt(discrim)) / 2 * a);
            System.out.printf("The equation has two roots: x1= %s x2= %s",
                    equationRoot.getRoot1(), equationRoot.getRoot2());
        }
    }

    private static float findDiscriminant(float a, float b, float c) {
        return b * b - 4 * a * c;
    }
}
