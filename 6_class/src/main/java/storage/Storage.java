package main.java.storage;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;

import java.util.Arrays;

public class Storage {
    public static final int ARR_CAPACITY = 1;

    public static Cargo[] arrCargo = new Cargo[ARR_CAPACITY];
    public static int currentIndexCargo = 0;

    public static Carrier[] arrCarrier = new Carrier[ARR_CAPACITY];
    public static int currentIndexCarrier = 0;

    public static Transportation[] arrTransportation = new Transportation[ARR_CAPACITY];
    public static int currentIndexTransportation = 0;

}
