package main.java;

import main.java.model.cargo.domain.Cargo;
import main.java.model.carrier.domain.Carrier;
import main.java.model.transportation.domain.Transportation;

import java.util.Arrays;

public class Storage {
    private static final int ARR_CAPACITY = 1;

    private Cargo[] arrCargo = new Cargo[ARR_CAPACITY];
    private Carrier[] arrCarrier = new Carrier[ARR_CAPACITY];
    private Transportation[] arrTransportation = new Transportation[ARR_CAPACITY];
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
                cargo.setId(IdGenerator.generateId());
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
                carrier.setId(IdGenerator.generateId());
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
                transportation.setId(IdGenerator.generateId());
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

    public Cargo getCargoById(Long id) {
        if (id != null && arrCargo.length != 0) {
            for (Cargo cargo : arrCargo) {
                if (id.equals(cargo.getId())) {
                    return cargo;
                }
            }
        }
        return null;
    }

    public Cargo getCargoByName(String name) {
        if (name != null && arrCargo.length != 0) {
            for (Cargo cargo : arrCargo) {
                if (name.equals(cargo.getName())) {
                    return cargo;
                }
            }
        }
        return null;
    }

    public Cargo[] getAllCargos() {
        if (arrCargo.length != 0) {
            return arrCargo;
        }
        return null;
    }

    public Carrier getCarrierById(Long id) {
        if (id != null && arrCarrier.length != 0) {
            for (Carrier carrier : arrCarrier) {
                if (id.equals(carrier.getId())) {
                    return carrier;
                }
            }
        }
        return null;
    }

    public Carrier getCarrierByName(String name) {
        if (name != null && arrCarrier.length != 0) {
            for (Carrier carrier : arrCarrier) {
                if (name.equals(carrier.getName())) {
                    return carrier;
                }
            }
        }
        return null;
    }

    public Carrier[] getAllCarriers() {
        if (arrCargo.length != 0) {
            return arrCarrier;
        }
        return null;
    }

    public Transportation getTransportationById(Long id) {
        if (id != null && arrTransportation.length != 0) {
            for (Transportation transportation : arrTransportation) {
                if (id.equals(transportation.getId())) {
                    return transportation;
                }
            }
        }
        return null;
    }

    public Transportation[] getAllTransportations() {
        if (arrTransportation.length != 0) {
            return arrTransportation;
        }
        return null;
    }
}
