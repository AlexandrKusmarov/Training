package common.solutions.utils.xml;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import carrier.domain.Carrier;
import carrier.domain.CarrierType;
import transportation.domain.Transportation;

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
