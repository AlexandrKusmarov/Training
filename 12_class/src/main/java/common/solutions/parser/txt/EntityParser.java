package main.java.common.solutions.parser.txt;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.carrier.domain.CarrierType;
import main.java.storage.IdGenerator;
import main.java.transportation.domain.Transportation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityParser {
    private static final String CARGOS = "CARGOS";
    private static final String CARRIERS = "CARRIERS";
    private static final String TRANSPORTATIONS = "TRANSPORTATIONS";
    private static boolean isFindStartEntity = false;
    private static final int MAX_ARR_CAPACITY_CARGO = 6;
    private static final int MAX_ARR_CAPACITY_CARRIER = 4;
    private static final int MAX_ARR_CAPACITY_TRANSPORTATION = 4;
    private static List<Cargo> cargosTemp = new ArrayList<>();
    private static List<Carrier> carriersTemp = new ArrayList<>();
    private static Map<String, Transportation> transportMapTemp = new HashMap<>();
    private static String line;
    private static Pattern p;
    private static Matcher m;

    public static void parseCargos(String text) throws ParseException {
        Scanner scanner = new Scanner(text);

        while (scanner.hasNextLine()) {
            if (!isFindStartEntity) {
                line = scanner.nextLine();
                p = Pattern.compile(CARGOS);
                m = p.matcher(line);
                if (m.find()) {
                    isFindStartEntity = true;
                }
            } else if (line != null) {
                line = scanner.nextLine();
                createCargo(splitStringbySpacesAndTakeValues(line));
                if (line.length() == 0) {
                    isFindStartEntity = false;
                    break;
                }
            }
        }
    }

    public static void parseCarriers(String text) {
        Scanner scanner = new Scanner(text);

        while (scanner.hasNextLine()) {
            if (!isFindStartEntity) {
                line = scanner.nextLine();
                p = Pattern.compile(CARRIERS);
                m = p.matcher(line);
                if (m.find()) {
                    isFindStartEntity = true;
                }
            } else if (line != null) {
                line = scanner.nextLine();
                createCarrier(splitStringbySpacesAndTakeValues(line));
                if (line.length() == 0) {
                    isFindStartEntity = false;
                    break;
                }
            }
        }
    }

    public static void parseTransportations(String text) throws ParseException {
        Scanner scanner = new Scanner(text);

        while (scanner.hasNextLine()) {
            if (!isFindStartEntity) {
                line = scanner.nextLine();
                p = Pattern.compile(TRANSPORTATIONS);
                m = p.matcher(line);
                if (m.find()) {
                    isFindStartEntity = true;
                }
            } else if (line != null) {
                line = scanner.nextLine();
                createTransportation(splitStringbySpacesAndTakeValues(line));
                if (line.length() == 0) {
                    isFindStartEntity = false;
                    break;
                }
            }
        }
    }

    private static List<Object> splitStringbySpacesAndTakeValues(String str) {
        List<Object> objects = new ArrayList<>();
        if (!str.isEmpty()) {
            String[] arr = str.split(" ");
            for (int i = 1; i < arr.length - 1; i++) {
                objects.add(arr[i].substring(arr[i].indexOf(":") + 1));
            }
        }
        return objects;
    }

    private static void createCargo(List<Object> objects) throws ParseException {
        Cargo cargo = null;

        if (objects.size() > 0 && objects.size() <= MAX_ARR_CAPACITY_CARGO) {
            if (objects.get(2).equals("FOOD")) {
                Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(objects.get(4).toString());
                Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse(objects.get(5).toString());
                cargo = new LimitedShelfLife(date1, date2);
            } else {
                cargo = new UnlimitedShelfLife(Boolean.parseBoolean(objects.get(4).toString()), Boolean.parseBoolean(objects.get(5).toString()));
            }
            cargo.setName(objects.get(0).toString());
            cargo.setWeight(Integer.valueOf((String) objects.get(1)));
            cargo.setCargoType(CargoType.valueOf(objects.get(2).toString()));
            cargo.setId(IdGenerator.generateId());
            cargosTemp.add(cargo);
        }
    }

    private static void createCarrier(List<Object> objects) {
        Carrier carrier = null;
        if (objects.size() > 0 && objects.size() <= MAX_ARR_CAPACITY_CARRIER) {
            carrier = new Carrier();
            carrier.setId(IdGenerator.generateId());
            carrier.setName(objects.get(0).toString());
            carrier.setAddress(objects.get(1).toString());
            carrier.setCarrierType(CarrierType.valueOf(objects.get(2).toString()));
            carriersTemp.add(carrier);
        }
    }

    private static void createTransportation(List<Object> objects) throws ParseException {
        Transportation transportation = null;

        if (objects.size() > 0 && objects.size() <= MAX_ARR_CAPACITY_TRANSPORTATION) {
            transportation = new Transportation();
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(objects.get(2).toString());
            transportation.setId(IdGenerator.generateId());
            transportation.setDescription(objects.get(0).toString());
            transportation.setBillTo(objects.get(1).toString());
            transportation.setDate(date);
            transportMapTemp.put(objects.get(3).toString(), transportation);
        }
    }

    public static void tieCargosCarriersToTransportations() {
        for (Map.Entry<String, Transportation> map : transportMapTemp.entrySet()) {
            String[] arr = map.getKey().split("_");
            map.getValue().setCargo(cargosTemp.get(Integer.parseInt(arr[0]) - 1));
            map.getValue().setCarrier(carriersTemp.get(Integer.parseInt(arr[0]) - 1));
        }
    }

    public static List<Cargo> getCargosTemp() {
        return cargosTemp;
    }

    public static List<Carrier> getCarriersTemp() {
        return carriersTemp;
    }

    public static Map<String, Transportation> getTransportMapTemp() {
        return transportMapTemp;
    }
}

