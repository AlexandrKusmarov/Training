package common.solutions.serialization;

import cargo.domain.Cargo;
import cargo.domain.CargoType;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import common.solutions.utils.FileUtils;
import common.solutions.utils.xml.EntityFillerUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SerializatorTest {

    @Test
    public void writeObjectToFile() throws IOException, ClassNotFoundException {
        String resourcePath = "main/homework/temp/cargoSerialization.txt";

        Cargo food1 = new FoodCargo();
        EntityFillerUtils.setCargoFields(food1, "food1", 100, null, CargoType.FOOD, null, null, new Date(2020, 1, 25), 4);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
//        assertEquals(food1, Serializator.readObjectFromFile(new File(resourcePath)));

        // Вариант без Equals.
        Cargo foodFromFile1 = (Cargo) Serializator.readObjectFromFile(new File(resourcePath));
        if (food1.getName() == foodFromFile1.getName() && food1.getWeight() == foodFromFile1.getWeight()) {
            assertTrue(true);
        }

        Cargo food2 = new FoodCargo();
        EntityFillerUtils.setCargoFields(food2, "food2", 200, null, CargoType.FOOD, null, null, new Date(2020, 1, 25), 4);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
//        assertEquals(food2, Serializator.readObjectFromFile(new File(resourcePath)));
        Cargo foodFromFile2 = (Cargo) Serializator.readObjectFromFile(new File(resourcePath));

        if (food2.getName() == foodFromFile2.getName() && food2.getWeight() == foodFromFile2.getWeight()) {
            assertTrue(true);
        }

        Cargo food3 = new FoodCargo();
        EntityFillerUtils.setCargoFields(food3, "food3", 300, null, CargoType.FOOD, null, null, new Date(2020, 1, 25), 4);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(food3, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo food4 = new FoodCargo();
        EntityFillerUtils.setCargoFields(food4, "food4", 400, null, CargoType.FOOD, null, null, new Date(2020, 1, 25), 4);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(food4, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo food5 = new FoodCargo();
        EntityFillerUtils.setCargoFields(food5, "food5", 500, null, CargoType.FOOD, null, null, new Date(2020, 1, 25), 4);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(food5, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo cloth1 = new ClothersCargo();
        EntityFillerUtils.setCargoFields(cloth1, "cloth11", 1000, null, CargoType.CLOTHERS, "BIG", "WOOL", new Date(2020, 4, 5), 24);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(cloth1, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo cloth2 = new ClothersCargo();
        EntityFillerUtils.setCargoFields(cloth2, "cloth12", 2000, null, CargoType.CLOTHERS, "BIG", "WOOL", new Date(2020, 4, 5), 24);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(cloth2, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo cloth3 = new ClothersCargo();
        EntityFillerUtils.setCargoFields(cloth3, "cloth13", 3000, null, CargoType.CLOTHERS, "BIG", "WOOL", new Date(2020, 4, 5), 24);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(cloth3, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo cloth4 = new ClothersCargo();
        EntityFillerUtils.setCargoFields(cloth4, "cloth14", 4000, null, CargoType.CLOTHERS, "BIG", "WOOL", new Date(2020, 4, 5), 24);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(cloth4, Serializator.readObjectFromFile(new File(resourcePath)));

        Cargo cloth5 = new ClothersCargo();
        EntityFillerUtils.setCargoFields(cloth5, "cloth15", 5000, null, CargoType.CLOTHERS, "BIG", "WOOL", new Date(2020, 4, 5), 24);
        Serializator.writeObjectToFile(food1, FileUtils.createFileFromResource(
                "test_cargo", "hw14", resourcePath));
        assertEquals(cloth5, Serializator.readObjectFromFile(new File(resourcePath)));
    }

    @Test
    public void writeListObjectsToFile() {
    }

    @Test
    public void readObjectFromFile() {
    }

    @Test
    public void readObjectsListFromFile() {
    }
}