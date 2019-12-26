package main.java.common.solutions.parser;

import main.java.cargo.domain.Cargo;
import main.java.cargo.domain.CargoType;
import main.java.cargo.domain.LimitedShelfLife;
import main.java.cargo.domain.UnlimitedShelfLife;
import main.java.carrier.domain.Carrier;
import main.java.storage.IdGenerator;
import main.java.transportation.domain.Transportation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityParser {
    private static final String CARGOS = "CARGOS";
    private static final String CARRIERS = "CARRIERS";
    private static final String TRANSPORTATIONS = "TRANSPORTATIONS";
    private static boolean isFindStartEntity = false;
    private static final int MAX_ARR_CAPACITY = 6;
    private static String line;
    private static Pattern p;
    private static Matcher m;

    public static void parse() {
        String text = EntityReader.stringBuilderFile.toString();
        try {
            parseCargos(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parseCarriers(text);
        parseTransportations(text);

    }

    public static List<Cargo> parseCargos(String text) throws ParseException {
        Scanner scanner = new Scanner(text);
        List<Cargo> temp = new ArrayList<>();

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
                Cargo cargo = createCargo(splitStringbySpacesAndTakeValues(line));
                if (cargo != null) {
                    temp.add(cargo);
                }
                System.out.println(cargo);
                if (line.length() == 0) {
                    break;
                }
            }
        }
        return temp;
    }

    public static Carrier parseCarriers(String text) {
        return null;
    }

    public static Transportation parseTransportations(String text) {
        return null;
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

    private static Cargo createCargo(List<Object> objects) throws ParseException {
        Cargo cargo = null;

        if (objects.size() > 0 && objects.size() <= MAX_ARR_CAPACITY) {
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
//            System.out.println(cargo);
        }
        return cargo;
    }
}
