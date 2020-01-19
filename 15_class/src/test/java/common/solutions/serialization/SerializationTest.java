package common.solutions.serialization;

import cargo.domain.Cargo;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import carrier.domain.Carrier;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import sun.rmi.transport.Transport;
import transportation.domain.Transportation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static common.solutions.comparator.SimpleComparator.LONG_COMPARATOR;
import static common.solutions.comparator.SimpleComparator.STRING_COMPARATOR;

public class SerializationTest {
    private Path tempFile = null;

    @Before
    public void createTempFile() throws IOException {
//        tempFile = Files.createFile (Paths.get("D:\\JAVA\\EPAM_SPB\\Training\\outputdata\\" +
//                RandomStringUtils.randomAlphabetic(8) + ".txt"));
//        System.out.println(tempFile.getFileName());
        tempFile = Files.createTempFile("temp", "test");
    }

    @AfterEach
    public void deleteTempFile() {
        deleteFile(tempFile);
    }

    @Test
    public void testSerializationFoodCargo() throws Exception {
        FoodCargo cargo = prepareFood();
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(cargo, pathToFile);
        FoodCargo deserialized = readSerializedObjectFromFile(pathToFile);


        Assertions.assertTrue(areFoodEntitiesEquals(cargo, deserialized));
    }

    @Test
    public void testSerializationFoodCargos() throws Exception {
        List<FoodCargo> foods = Arrays.asList(prepareFood(), prepareFood());
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(foods, pathToFile);
        List<FoodCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areFoodEntitiesEquals(foods, deserialized));
    }

    @Test
    public void testSerializationFoodNullCargo() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationClothersCargo() throws Exception {
        ClothersCargo clothers = prepareClothers();
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(clothers, pathToFile);
        ClothersCargo deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    }

    @Test
    public void testSerializationClothersCargos() throws Exception {
        List<ClothersCargo> clothers = Arrays.asList(prepareClothers(), prepareClothers());
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(clothers, pathToFile);
        List<ClothersCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    }

    @Test
    public void testSerializationClothersNullCargos() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationCarrier() throws Exception {
        Carrier carrier = prepareCarrier();
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(carrier, pathToFile);
        Carrier deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areCarrierEntitiesEquals(carrier, deserialized));
    }

    @Test
    public void testSerializationNullCarrier() throws Exception {
        Carrier carrier = null;
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(carrier, pathToFile);
        Carrier deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationCarriers() throws Exception {
        List <Carrier> carrierList = Arrays.asList(prepareCarrier(), prepareCarrier());
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(carrierList, pathToFile);
        List<Carrier> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areCarrierEntitiesEquals(carrierList, deserialized));
    }

    @Test
    public void testSerializationTransportation() throws Exception {
        Transportation transportation = prepareTransportation(prepareFood(),prepareCarrier());
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(transportation, pathToFile);
        Transportation deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areTransportationEntitiesEquals(transportation, deserialized));
    }


    private <T> void serializeToFile(T entity, String file) throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(entity);
        }
    }

    private <T> T readSerializedObjectFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (T) objectInputStream.readObject();
        }
    }

    private FoodCargo prepareFood() {
        FoodCargo food = new FoodCargo();
        food.setId(randLong());
        food.setName(randString());
        food.setWeight(randInt());
        food.setStoreTemperature(randInt());
        food.setExpirationDate(new Date());

        return food;
    }

    private ClothersCargo prepareClothers() {
        ClothersCargo clothers = new ClothersCargo();
        clothers.setName(randString());
        clothers.setId(randLong());
        clothers.setSize(randString());
        clothers.setWeight(randInt());
        clothers.setMaterial(randString());

        return clothers;
    }

    private Carrier prepareCarrier() {
        Carrier carrier = new Carrier();
        carrier.setId(randLong());
        carrier.setName(randString());
        carrier.setAddress(randString());

        return carrier;
    }

    private Transportation prepareTransportation(Cargo cargo, Carrier carrier){
        Transportation transportation = new Transportation();
        transportation.setId(randLong());
        transportation.setDescription(randString());
        transportation.setBillTo(randString());
        transportation.setCargo(cargo);
        transportation.setCarrier(carrier);

        return transportation;
    }

    private String randString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    private int randInt() {
        return RandomUtils.nextInt();
    }

    private long randLong() {
        return RandomUtils.nextLong();
    }

    private boolean areFoodEntitiesEquals(FoodCargo food1, FoodCargo food2) {
        if (food1 == null && food2 == null) {
            return true;
        } else if (food1 != null && food2 == null) {
            return false;
        } else if (food1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(food1.getName(), food2.getName()) == 0
                    && LONG_COMPARATOR.compare(food1.getId(), food2.getId()) == 0
                    && food1.getWeight() == food2.getWeight()
                    && food1.getStoreTemperature() == food2.getStoreTemperature();
            //continue in this way
        }
    }

    private boolean areFoodEntitiesEquals(List<FoodCargo> foods1, List<FoodCargo> foods2) {
        if (foods1 == null && foods2 == null) {
            return true;
        } else if (foods1 != null && foods2 == null) {
            return false;
        } else if (foods1 == null) {
            return false;
        } else if (foods1.size() != foods2.size()) {
            return false;
        } else {
            for (int i = 0; i < foods1.size(); i++) {
                if (!areFoodEntitiesEquals(foods1.get(i), foods2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areClotherEntitiesEquals(ClothersCargo clother1, ClothersCargo clother2) {
        if (clother1 == null && clother2 == null) {
            return true;
        } else if (clother1 != null && clother2 == null) {
            return false;
        } else if (clother1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(clother1.getName(), clother2.getName()) == 0
                    && LONG_COMPARATOR.compare(clother1.getId(), clother2.getId()) == 0
                    && STRING_COMPARATOR.compare(clother1.getMaterial(), clother2.getMaterial()) == 0;
            //continue in this way
        }
    }

    private boolean areClotherEntitiesEquals(List<ClothersCargo> clothers1,
                                             List<ClothersCargo> clothers2) {
        if (clothers1 == null && clothers2 == null) {
            return true;
        } else if (clothers1 != null && clothers2 == null) {
            return false;
        } else if (clothers1 == null) {
            return false;
        } else if (clothers1.size() != clothers2.size()) {
            return false;
        } else {
            for (int i = 0; i < clothers1.size(); i++) {
                if (!areClotherEntitiesEquals(clothers1.get(i), clothers2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areCarrierEntitiesEquals(Carrier carrier1, Carrier carrier2) {
        if (carrier1 == null && carrier2 == null) {
            return true;
        } else if (carrier1 != null && carrier2 == null) {
            return false;
        } else if (carrier1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(carrier1.getName(), carrier2.getName()) == 0
                    && LONG_COMPARATOR.compare(carrier1.getId(), carrier2.getId()) == 0
                    && STRING_COMPARATOR.compare(carrier1.getAddress(), carrier2.getAddress()) == 0;
            //continue in this way
        }
    }

    private boolean areCarrierEntitiesEquals(List<Carrier> carriers1,
                                             List<Carrier> carriers2) {
        if (carriers1 == null && carriers2 == null) {
            return true;
        } else if (carriers1 != null && carriers2 == null) {
            return false;
        } else if (carriers1 == null) {
            return false;
        } else if (carriers1.size() != carriers2.size()) {
            return false;
        } else {
            for (int i = 0; i < carriers1.size(); i++) {
                if (!areCarrierEntitiesEquals(carriers1.get(i), carriers1.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areTransportationEntitiesEquals(Transportation transportation1, Transportation transportation2) {
        if (transportation1 == null && transportation2 == null) {
            return true;
        } else if (transportation1 != null && transportation2 == null) {
            return false;
        } else if (transportation1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(transportation1.getDescription(), transportation2.getDescription()) == 0
                    && LONG_COMPARATOR.compare(transportation1.getId(), transportation2.getId()) == 0
                    && STRING_COMPARATOR.compare(transportation1.getBillTo(), transportation2.getBillTo()) == 0;
            //continue in this way
        }
    }

    private void deleteFile(Path path) {
        if (path != null && path.toFile().isFile()) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}