package main.java.model.common.domain;

public class Basic {
    private Long cargoID;
    private Long carrierID;
    private Long transportationID;

    public Basic(Long cargoID, Long carrierID, Long transportationID) {
        this.cargoID = cargoID;
        this.carrierID = carrierID;
        this.transportationID = transportationID;
    }

    public Long getCargoID() {
        return cargoID;
    }

    public void setCargoID(Long cargoID) {
        this.cargoID = cargoID;
    }

    public Long getCarrierID() {
        return carrierID;
    }

    public void setCarrierID(Long carrierID) {
        this.carrierID = carrierID;
    }

    public Long getTransportationID() {
        return transportationID;
    }

    public void setTransportationID(Long transportationID) {
        this.transportationID = transportationID;
    }
}
