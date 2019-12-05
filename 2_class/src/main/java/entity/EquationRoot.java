package main.java.entity;

public class EquationRoot {
    private float root1;
    private float root2;

    public EquationRoot() {
    }

    public EquationRoot(float root1, float root2) {
        this.root1 = root1;
        this.root2 = root2;
    }

    public EquationRoot(float root1) {
        this.root1 = root1;
    }

    public float getRoot1() {
        return root1;
    }

    public void setRoot1(float root1) {
        this.root1 = root1;
    }

    public float getRoot2() {
        return root2;
    }

    public void setRoot2(float root2) {
        this.root2 = root2;
    }
}
