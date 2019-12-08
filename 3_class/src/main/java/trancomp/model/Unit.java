package main.java.trancomp.model;

public class Unit {
    private double price;
    private double weight;
    private double height;
    private double width;
    private double longBox;

    public Unit(double price, double weight, double height, double width, double longBox) {
        this.price = price;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.longBox = longBox;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLongBox() {
        return longBox;
    }

    public void setLongBox(double longBox) {
        this.longBox = longBox;
    }
}
