package main.java.storage.initor.fileinitor;

import main.java.application.serviceholder.ServiceHolder;
import main.java.cargo.service.CargoService;
import main.java.carrier.service.CarrierService;
import main.java.storage.initor.StorageInitor;
import main.java.transportation.service.TransportationService;

public class SaxXmlStorageInitor implements StorageInitor {
    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public SaxXmlStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {

    }

    private void initCarriers() {

    }

    private void initTransportations() {

}
