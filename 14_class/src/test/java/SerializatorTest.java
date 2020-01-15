package test.java;

import main.homework.cargo.domain.Cargo;
import main.homework.cargo.domain.ClothersCargo;
import main.homework.cargo.domain.FoodCargo;
import org.junit.Before;
import org.junit.gen5.api.Test;

import java.io.File;
import java.io.IOException;

public class SerializatorTest {

    @Before
    public void createCargos(){
        Cargo food1 = new FoodCargo();
        Cargo food2 = new FoodCargo();
        Cargo food3 = new FoodCargo();
        Cargo food4 = new FoodCargo();
        Cargo food5 = new FoodCargo();
        Cargo cloth1 = new ClothersCargo();
        Cargo cloth2 = new ClothersCargo();
        Cargo cloth3 = new ClothersCargo();
        Cargo cloth4 = new ClothersCargo();
        Cargo cloth5 = new ClothersCargo();
    }

    @Test
    public static void writeObjectToFile(Object object, File outputFile) throws IOException {

    }
}
