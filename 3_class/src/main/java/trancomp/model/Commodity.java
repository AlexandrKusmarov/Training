package main.java.trancomp.model;

/**
 * Category of the goods (food, drinks, auto details, appliances e.t.c...)
 */

public class Commodity {
    private String category;
    private String name;
    private Unit unit;

    public Commodity() {
    }

    public Commodity(String category, String name, Unit unit) {
        this.category = category;
        this.name = name;
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
