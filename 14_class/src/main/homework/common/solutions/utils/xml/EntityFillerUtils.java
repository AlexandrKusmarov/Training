package main.homework.common.solutions.utils.xml;

import main.homework.cargo.domain.Cargo;
import main.homework.cargo.domain.CargoType;
import main.homework.cargo.domain.ClothersCargo;
import main.homework.cargo.domain.FoodCargo;
import main.homework.carrier.domain.Carrier;
import main.homework.carrier.domain.CarrierType;
import main.homework.transportation.domain.Transportation;

import java.util.Date;
import java.util.List;

public class EntityFillerUtils {
    private EntityFillerUtils() {
    }

    public static void setCargoFields(Cargo cargo, String name, int weight,
                                      List<Transportation> transportationList,
                                      CargoType cargoType, String size,
                                      String material, Date expirationDate,
                                      int storageTemperature) {
        cargo.setName(name);
        cargo.setWeight(weight);
        cargo.setTransportations(transportationList);
        if(cargo.getClass().equals(FoodCargo.class)){
            ((FoodCargo)cargo).setExpirationDate(expirationDate);
            ((FoodCargo)cargo).setStoreTemperature(storageTemperature);
        } else {
            ((ClothersCargo) cargo).setSize(size);
            ((ClothersCargo) cargo).setMaterial(material);
        }
    }

    public static void setCarrierFields(Carrier carrier, String name, String address,
                                        CarrierType carrierType,
                                        List<Transportation> transportationList ){
        carrier.setName(name);
        carrier.setAddress(address);
        carrier.setCarrierType(carrierType);
        carrier.setTransportations(transportationList);
    }

    public static void setTransportationFields(Transportation transportation, Cargo cargo,
                                               Carrier carrier, String description,
                                               String billTo, Date transportationBeginDate){
        transportation.setCargo(cargo);
        transportation.setCarrier(carrier);
        transportation.setDescription(description);
        transportation.setBillTo(billTo);
        transportation.setTransportationBeginDate(transportationBeginDate);
    }
}
