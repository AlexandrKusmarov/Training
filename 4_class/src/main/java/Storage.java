package main.java;

import main.java.model.cargo.Cargo;
import main.java.model.carrier.Carrier;
import main.java.model.transportation.Transportation;

import java.util.Arrays;

public class Storage {
    private Cargo[] arrCargo = new Cargo[1];
    private Carrier[] arrCarrier = new Carrier[1];
    private Transportation[] arrTransportation = new Transportation[1];
    private int currentIndexCargo = 0;
    private int currentIndexCarrier = 0;
    private int currentIndexTransportation = 0;

    private Object[] expandArrCapacity(Object[] array) {
        Object[] tempArr;
        tempArr = new Object[array.length * 2];
        tempArr = Arrays.copyOf(array, tempArr.length);
        return tempArr;
    }

    public void addCargo(Cargo cargo) {
        if (cargo != null) {
            if (currentIndexCargo < arrCargo.length) {
                arrCargo[currentIndexCargo] = cargo;
                currentIndexCargo++;
            } else {
                arrCargo = (Cargo[]) expandArrCapacity(arrCargo);
                addCargo(cargo);
            }
        }
    }

    public void addCarrier(Carrier carrier) {
        if (carrier != null) {
            if (currentIndexCarrier < arrCarrier.length) {
                arrCarrier[currentIndexCarrier] = carrier;
                currentIndexCarrier++;
            } else {
                arrCarrier = (Carrier[]) expandArrCapacity(arrCarrier);
                addCarrier(carrier);
            }
        }
    }

    public void addTransportation(Transportation transportation) {
        if (transportation != null) {
            if (currentIndexTransportation < arrTransportation.length) {
                arrTransportation[currentIndexTransportation] = transportation;
                currentIndexTransportation++;
            } else {
                arrTransportation = (Transportation[]) expandArrCapacity(arrTransportation);
                addTransportation(transportation);
            }
        }
    }

    public void printAllCargo() {
        for (Cargo cargo : arrCargo) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }

    public void printAllCarrier() {
        for (Carrier carrier : arrCarrier) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    public void printAllTransportation() {
        for (Transportation transportation : arrTransportation) {
            if (transportation != null) {
                System.out.println(transportation);
            }
        }
    }
}
