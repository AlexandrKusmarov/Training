package main.java.cargo.domain.comparator;

import main.java.cargo.domain.Cargo;

import java.util.Comparator;

public class CargoWeightComparator implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
       return o1.getWeight() - o2.getWeight();
    }
}
