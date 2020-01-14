package main.java.common.solutions.util.xml;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;

import java.util.Map;

public class CommonParserUtil {
    private CommonParserUtil() {
    }

    public static void tieCargosCarriersToTransportations(Map<String, Transportation> map, Map<String, Cargo> cargoMap,
                                                          Map<String, Carrier> carrierMap) {

        for (Map.Entry<String, Transportation> pair : map.entrySet()) {
            String[] arr = pair.getKey().split("->");
            pair.getValue()
                    .setCargo(getCargoFromMap(cargoMap, arr[0]));
            pair.getValue()
                    .setCarrier(getCarrierFromMap(carrierMap, arr[1]));
        }
    }

    private static Cargo getCargoFromMap(Map<String, Cargo> cargoMap, String idForParsing) {
        for (Map.Entry<String, Cargo> pair : cargoMap.entrySet()) {
            String id = pair.getKey();
            if (id.equals(idForParsing.trim())) {
                return pair.getValue();
            }
        }
        return null;
    }

    private static Carrier getCarrierFromMap(Map<String, Carrier> carrierMap, String idForParsing) {
        for (Map.Entry<String, Carrier> pair : carrierMap.entrySet()) {
            String id = pair.getKey();
            if (id.equals(idForParsing.trim())) {
                return pair.getValue();
            }
        }
        return null;
    }
}
