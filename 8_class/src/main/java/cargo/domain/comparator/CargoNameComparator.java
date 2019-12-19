package main.java.cargo.domain.comparator;

import main.java.cargo.domain.Cargo;

import java.util.Comparator;

public class CargoNameComparator implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
