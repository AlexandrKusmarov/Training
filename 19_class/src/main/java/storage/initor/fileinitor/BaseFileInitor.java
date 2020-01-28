package storage.initor.fileinitor;


import application.serviceholder.ServiceHolder;
import cargo.domain.Cargo;
import carrier.domain.Carrier;
import storage.initor.StorageInitor;
import transportation.domain.Transportation;

import java.util.*;

public abstract class BaseFileInitor implements StorageInitor {

    protected void setReferencesBetweenEntities(Map<String, Cargo> cargoMap,
                                                Map<String, Carrier> carrierMap, List<ParsedTransportation> parsedTransportations) {

        for (ParsedTransportation parsedTransportation : parsedTransportations) {
            Optional<Cargo> cargo = Optional.ofNullable(cargoMap.get(parsedTransportation.cargoRef));
            Optional<Carrier> carrier = Optional.ofNullable(carrierMap.get(parsedTransportation.carrierRef));
            Transportation transportation = parsedTransportation.transportation;
//      Cargo cargo = cargoMap.get(parsedTransportation.cargoRef);
//      Carrier carrier = carrierMap.get(parsedTransportation.carrierRef);
//      if(cargo!= null){

            if (cargo.isPresent()) {
                List<Transportation> transportations =
                        cargo.get().getTransportations() == null ? new ArrayList<>() : cargo.get().getTransportations();
                transportations.add(transportation);
                transportation.setCargo(cargo.get());
                cargo.get().setTransportations(transportations);
            }

//      if (carrier != null) {
            if (carrier.isPresent()) {
                List<Transportation> transportations =
                        carrier.get().getTransportations() == null ? new ArrayList<>() : carrier.get().getTransportations();
                transportations.add(transportation);
                transportation.setCarrier(carrier.get());
                carrier.get().setTransportations(transportations);
            }
        }
    }

    protected void persistCargos(Collection<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            ServiceHolder.getInstance().getCargoService().save(cargo);
        }
    }

    protected void persistCarriers(Collection<Carrier> carriers) {
        for (Carrier carrier : carriers) {
            ServiceHolder.getInstance().getCarrierService().save(carrier);
        }
    }

    protected List<Transportation> getTransportationsFromParsedObject(
            List<ParsedTransportation> transportations) {
        List<Transportation> result = new ArrayList<>();
        for (ParsedTransportation transportation : transportations) {
            result.add(transportation.transportation);
        }

        return result;
    }

    protected void persistTransportations(List<Transportation> transportations) {
        for (Transportation transportation : transportations) {
            ServiceHolder.getInstance().getTransportationService().save(transportation);
        }
    }

    public static class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;

        public String getCargoRef() {
            return cargoRef;
        }

        public void setCargoRef(String cargoRef) {
            this.cargoRef = cargoRef;
        }

        public String getCarrierRef() {
            return carrierRef;
        }

        public void setCarrierRef(String carrierRef) {
            this.carrierRef = carrierRef;
        }

        public Transportation getTransportation() {
            return transportation;
        }

        public void setTransportation(
                Transportation transportation) {
            this.transportation = transportation;
        }
    }

}
