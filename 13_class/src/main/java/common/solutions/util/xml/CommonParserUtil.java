package main.java.common.solutions.util.xml;

import main.java.cargo.domain.Cargo;
import main.java.carrier.domain.Carrier;
import main.java.transportation.domain.Transportation;

import java.util.List;
import java.util.Map;

public class CommonParserUtil {
    private static final int CARGO_INDX_FROM_XML = 1000;
    private static final int CARRIER_INDEX_FROM_XML = 2000;

    private CommonParserUtil() {
    }

    public static void tieCargosCarriersToTransportations(Map<String, Transportation> map, List<Cargo> cargoList,
                                                          List<Carrier> carrierList) {

        for (Map.Entry<String, Transportation> pair : map.entrySet()) {
            String[] arr = pair.getKey().split("->");
            pair.getValue()
                    .setCargo(cargoList.get(
                            Integer.parseInt(arr[0].trim()) - CARGO_INDX_FROM_XML - 1));
            pair.getValue()
                    .setCarrier(carrierList.get(
                            Integer.parseInt(arr[1].trim()) - CARRIER_INDEX_FROM_XML - 1));
        }
    }
}
